package com.xxx.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.server.config.security.JwtTokenUtil;
import com.xxx.server.mapper.AdminMapper;
import com.xxx.server.pojo.Admin;
import com.xxx.server.pojo.Menu;
import com.xxx.server.pojo.ResultOV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2022-01-14
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements EmployeeEcServiceImpl.IAdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * &#x767b;&#x5f55;&#x4e4b;&#x540e;&#x8fd4;&#x56de;token
     * @param username
     * @param password
     * @param request
     * @return
     */
    @Override
    public ResultOV login(String username, String password, String code,HttpServletRequest request) {
        //判断验证码是否正确
        String captcha = (String)request.getSession().getAttribute("captcha");
        if(StringUtils.isBlank(code) || !captcha.equals(code)){
            return ResultOV.error("验证码错误！");
        }
        //登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(userDetails == null || !passwordEncoder.matches(password,userDetails.getPassword())){
            return  ResultOV.error("用户名或密码不正确");
        }
        if(!userDetails.isEnabled()){
            return ResultOV.error("账号被禁用，请联系管理员");
        }
        //更新 security 登录用户名对象
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //生成 token
        String token = jwtTokenUtil.getToken(userDetails);
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        return ResultOV.success("登录成功",tokenMap);
    }

    /**
     * 根据用户名获取用户
     * @param name
     * @return
     */
    @Override
    public Admin getAdminByUserName(String name) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",name).eq("enabled",true));
    }
}
