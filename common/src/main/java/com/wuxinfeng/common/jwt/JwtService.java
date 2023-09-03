package com.wuxinfeng.common.jwt;

import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.gson.io.GsonSerializer;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @Description
 * @Author Wu Xinfeng
 * @Date 2023/2/24 12:31
 **/
@Service
public class JwtService {

    public String extractUsername(Map<String, String> headers){
        String authHeader = headers.get(JwtConstant.tokenHeader);
        String jwt = authHeader.substring(JwtConstant.tokenHead.length());
        return extractUsername(jwt);
    }


    public String extractUsername(String token){
        return extractClaim(token,Claims::getSubject);
    }
    public List<String> extractRoles(String token){
        Claims claims = extractAllClaims(token);
        return (List<String>) claims.get(JwtConstant.JWT_ROLES);
    }

    public List<String> extractPlantScopes(String token){
        Claims claims = extractAllClaims(token);
        return (List<String>) claims.get(JwtConstant.PLANT_SCOPES);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsTFunction){
        final Claims claims=extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }


    /**
     * @description: 设置传进jwt的用户相关的信息
     * @author: Wu Xinfeng
     * @date: 2023/3/21 9:23
     * @email: 390155409@qq.com
     **/
    public String generateToken(String username){
        HashMap<String, String> claims = new HashMap<>();
//        claims.put("uid",userDetails.getUsername());
        return generateToken(new HashMap<>(),username);
    }

    public String generateToken(Map<String,Object> extraClaims,String username){
        return generateToken(extraClaims,username,JwtConstant.expired);
    }

    public String generateToken(Map<String,Object> extraClaims,String username,long expired){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expired))
                .signWith(getSignInKey(),SignatureAlgorithm.HS256)
                .serializeToJsonWith(new GsonSerializer<>(new Gson()))
                .compact();
    }

    public boolean isTokenValid(String token){
        try {
            Claims claims = extractAllClaims(token);
            if (claims != null) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);

    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())//todo 这里可能有问题
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes= Decoders.BASE64.decode(JwtConstant.secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
