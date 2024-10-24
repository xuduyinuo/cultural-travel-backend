package com.xudu.culturaltravelbackend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class JWTUtil {

    //日志
    private static final Logger log = LoggerFactory.getLogger(JWTUtil.class);

    private static final String SECRET = "javaabcdefg";

    // 过期时间10分钟
    private static final long EXPIRE_TIME = 10 * 60 * 1000;

    /**
     * 生成签名,10min后过期
     *
     * @param userAccount 用户账号
     * @return 加密的token
     */
    public static String sign(String userAccount) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        // 附带username信息
        return JWT.create()
                .withClaim("userAccount", userAccount)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @return 是否正确
     */
    public static boolean verify(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        return true;
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户账号
     */
    public static String getUserAccount(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("userAccount").asString();
    }

}