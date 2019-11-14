package com.koowakchai.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
    private static Logger logger = LoggerFactory.getLogger(JWTUtils.class);
    private static final String SECRET = "MyPrivateKey";
    private static final long EXPIRATION_TIME  = 1 * 24 * 60 * 60 * 1000;

    /**
     * JWT generate Token.<br/>
     *
     * JWT: header, payload, signature
     *
     * @param user_id
     *            user_id after login success, user_id cannot be empty
     */
    public static String createToken(Long user_id) throws Exception {
        logger.info("Create token!!!");
        String token = "";
        try{
            Date iatDate = new Date();
            long nowMillis = System.currentTimeMillis();
            long expMillis = nowMillis + EXPIRATION_TIME;
            Date expiresDate = new Date(expMillis);
// header Map
            Map<String, Object> map = new HashMap<>();
            map.put("alg", "HS256");
            map.put("typ", "JWT");
            // build token
            // param backups {iss:Service, aud:APP}
            token = JWT.create().withHeader(map) // header
                    .withClaim("iss", "Service") // payload
                    .withClaim("aud", "APP").withClaim("userId", null == user_id ? null : user_id.toString())
                    .withIssuedAt(iatDate) // sign time
                    .withExpiresAt(expiresDate) // expire time
                    .sign(Algorithm.HMAC256(SECRET)); // signature
        }
        catch (JWTCreationException exception){
            logger.error("HS256 encryption error!!!",exception.getMessage());
        }
        return token;
    }

    /**
     * Unsign and verify Token
     *
     * @param token
     * @return
     * @throws Exception
     */

    public static Map<String, Claim> verifyToken(String token) throws Exception{
        DecodedJWT jwt = null;
        logger.info("start identify token...");
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        jwt = verifier.verify(token);
        Map<String, Claim> claims = jwt.getClaims();
        System.out.println("成功了？ " + claims.get("userId").asString());
        return jwt.getClaims();

    }

    /**
     * get user_id
     *
     * @param token
     * @return user_id
     */

    public static Long getUID(String token) throws Exception{
        Map<String, Claim> claims = verifyToken(token);
        Claim user_id_claim = claims.get("user_id");
        if (null == user_id_claim || StringUtils.isEmpty(user_id_claim.asString())) {
            return 0L;
        }
        logger.info("get userid " + Long.valueOf(user_id_claim.asString()));
        return Long.valueOf(user_id_claim.asString());
    }


}
