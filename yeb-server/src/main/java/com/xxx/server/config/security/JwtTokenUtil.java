package com.xxx.server.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.management.Query;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {
    //用户名
    private static  final String CLAIM_KEY_USERNAME="sub";
    //Token创建时间
    private static  final String CLAIM_KEY_CREATED="created";
    //秘钥
    @Value("${jwt.secret}")
    private String secret;
    //有效期
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 生成 jwt，传入自定义的负载信息
     */
    private String createToken(Map<String,Object> map){
        return Jwts.builder()
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis()+ expiration* 1000) )
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    /**
     * 根据自定义负载信息，获取Token
     */
    public String getToken(UserDetails userDetails){
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED,new Date());
        return createToken(claims);
    }

    /**
     * 获取Token中的用户信息
     */
    public String getUserNameFromToken(String token){
        String userName;
        try {
            Claims claims = getClaims(token);
            userName = claims.getSubject();
        }catch (Exception e){
            userName = null;
        }
        return userName;
    }
    /**
     * 获取负载信息
     */
    public Claims getClaims(String token){
        Claims claims=null;

        try{
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

        }catch (Exception e){
            e.printStackTrace();
        }
         return claims;
    }
    /**
     * 获取 token 有效期
     */
    public Date getExpiredDateFromToken(String token){
        Claims claims = getClaims(token);
        return claims.getExpiration();
    }
    /**
     * 判断 token 是否过期, 如果有效期在当前日期前面，就是失效
     */
    public boolean isTokenExpired(String token){
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }
    /**
     * 刷新 token,就是更新 token 创建时间
     */
    public String refreshToken(String token){
        Claims claims = getClaims(token);
        claims.put(CLAIM_KEY_CREATED,new Date());
        return createToken(claims);
    }
    /**
     * 判断 token 是否可刷新，过期 可以刷新
     */
    public boolean isCanRefresh(String token){
        return !isTokenExpired(token);
    }
    /**
     * 验证 token 是否有效
     */
    public boolean isVaildateToken(String token,UserDetails userDetails){
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

}
