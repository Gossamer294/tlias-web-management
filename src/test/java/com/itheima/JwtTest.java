package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

public class JwtTest {
    @Test
    // 生成JWT令牌
    public void testGenerateJwt(){
        Map<String, Object> dataMap=new HashMap<>();
        dataMap.put("id",1);
        dataMap.put("username","admin");
        String jwt =Jwts.builder().signWith(HS256,"aXRoZWltYQ==")//签名算法
                .addClaims(dataMap)//添加自定义数据
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24))//设置过期时间
                .compact();//生成JWT令牌
        System.out.println(jwt);


    }
    @Test
    // 解析JWT令牌
    public void testParseJwt(){
        String a= "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc3NjUxMDM1M30.BNM3AhkKbuY9SHYQLCVpon2wNcH3WcUNqIWz4jMPoOE";

        Claims claims =Jwts.parser().setSigningKey("aXRoZWltYQ==")
                .parseClaimsJws(a)
        .getBody();
        System.out.println(claims);
    }
}
