package com.power.setting.utills;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.power.setting.Contains.Contains;


public class TwtUtil {
    //过期时间
        /**
         * 生成签名，15分钟过期
         *
         * @param **username**
         * @param **password**
         * @return
         */
        public static String createToken(String userId) {
            try {
                // 设置过期时间
                Date date = new Date(System.currentTimeMillis() + Contains.EXPIRE_TIME);
                // 私钥和加密算法
                Algorithm algorithm = Algorithm.HMAC256(Contains.TOKEN_SECRET);
                // 设置头部信息
                Map<String, Object> header = new HashMap<>(2);
                header.put("Type", "Jwt");
                header.put("alg", "HS256");
                // 返回token字符串
                return JWT.create()
                        .withHeader(header)
                        .withClaim("userId", userId)
                        .withExpiresAt(date)
                        .sign(algorithm);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /**
         * 生成token，自定义过期时间 毫秒
         *
         * @param **username**
         * @param **password**
         * @return
         */
        public static String createToken(String userId, long expireDate) {
            try {
                // 设置过期时间
                Date date = new Date(System.currentTimeMillis() + expireDate);
                // 私钥和加密算法
                Algorithm algorithm = Algorithm.HMAC256(Contains.TOKEN_SECRET);
                // 设置头部信息
                Map<String, Object> header = new HashMap<>(2);
                header.put("Type", "Jwt");
                header.put("alg", "HS256");
                // 返回token字符串
                return JWT.create()
                        .withHeader(header)
                        .withClaim("userId", userId)
                        .withExpiresAt(date)
                        .sign(algorithm);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        /**
         * 检验token是否正确
         * @param **token**
         * @return
         */
        public static String verifyToken(String token){
            try {
                Algorithm algorithm = Algorithm.HMAC256(Contains.TOKEN_SECRET);
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT jwt = verifier.verify(token);
                String userId = jwt.getClaim("userId").asString();
                return userId;
            } catch (Exception e){
                return null;
            }
        }
    }


