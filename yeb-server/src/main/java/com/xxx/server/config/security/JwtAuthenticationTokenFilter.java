package com.xxx.server.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT 登陆授权管理器
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader(tokenHeader);
        //存在token，注意，一般 token前面还有一个 “Bearer ”
        if(authHeader!= null && authHeader.startsWith(tokenHead)){
            String authToken = authHeader.substring(tokenHead.length());
            //获取 token中的用户名
            String username = jwtTokenUtil.getUserNameFromToken(authToken);
            //有用户名，但是没有用户对象，即未登录
            if(username != null && SecurityContextHolder.getContext().getAuthentication() ==null){
                //登陆
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                //验证 token 是否有效，包括 token 和 userDetails 中用户名是否一样，新设置用户对象
                if(jwtTokenUtil.isVaildateToken(authToken,userDetails)){
                    //生成通过认证的 Authentication
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    //将这个请求本体存入 Authentication，后面还会使用 Authentication 中的保存的信息
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
