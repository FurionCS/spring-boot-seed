
package com.company.project.jwt;

import com.company.common.exception.GlobalException;
import com.company.common.model.StatusCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.*;

/**
 * Token生成工具类
 * <p/>
 */
@Component
@NoArgsConstructor
public class JsonWebTokenUtility {
    private static SignatureAlgorithm signatureAlgorithm;
    static{
        //算法
        signatureAlgorithm = SignatureAlgorithm.HS512;
    }
    /**
     * 秘钥
     */
    @Value("${jwt.encodedKey:L7A/6zARSkK1j7Vd5SDD9pSSqZlqF7mAhiOgRbgv9Smce6tf4cJnvKOjtKPxNNnWQj+2lQEScm3XIUjhW+YVZg==}")
    private String encodedKey;
    /**
     * 过期时间 单位分 默认120
     */
    @Value("${jwt.expireTime:120}")
    private Integer expire;
    private Key secretKey;
    public void init(){
        //密钥
        if(Objects.isNull(secretKey)) {
            secretKey = deserializeKey(encodedKey);
        }
    }
    /**
     * 创建jwt token
     * @param authTokenDetails
     * @return
     */
    public String createJsonWebToken(AuthTokenDetails authTokenDetails) {
        String token =
                Jwts.builder().setSubject(authTokenDetails.getId().toString())
                        .claim("username", authTokenDetails.getUsername())
                        .claim("roleNames", authTokenDetails.getRoleNames())
                        .setExpiration(buildExpirationDate(expire))
                        .signWith(getSignatureAlgorithm(),
                                getSecretKey()).compact();
        return token;
    }

    private Key deserializeKey(String encodedKey) {
        byte[] decodedKey = Base64.getMimeDecoder().decode(encodedKey);
        Key key =
                new SecretKeySpec(decodedKey, getSignatureAlgorithm().getJcaName());
        return key;
    }

    private Key getSecretKey() {
        return secretKey;
    }

    public SignatureAlgorithm getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    /**
     * 判断获取token信息，如果为null则表示 token 过期或者不正确
     * @param token
     * @return
     */
    public AuthTokenDetails parseAndValidate(String token) {
        AuthTokenDetails authTokenDetails = null;
        try {
            Claims claims =
                    Jwts.parser().setSigningKey(getSecretKey()).parseClaimsJws(token).getBody();
            String userId = claims.getSubject();
            String username = (String) claims.get("username");
            List<String> roleNames = (List) claims.get("roleNames");
            Date expirationDate = claims.getExpiration();

            authTokenDetails = new AuthTokenDetails();
            authTokenDetails.setId(Long.valueOf(userId));
            authTokenDetails.setUsername(username);
            authTokenDetails.setRoleNames(roleNames);
            authTokenDetails.setExpirationDate(expirationDate);
        } catch (JwtException ex) {
            throw new GlobalException(ex.getMessage(), StatusCode.Invalid_Token_ReLogin);
        }
        return authTokenDetails;
    }

    private String serializeKey(Key key) {
        String encodedKey =
                Base64.getEncoder().encodeToString(key.getEncoded());
        return encodedKey;
    }

    /**
     * 刷新token
     * @param token
     * @return
     */
    public String refeshToken(String token){
        AuthTokenDetails authTokenDetails=parseAndValidate(token);
        if(authTokenDetails==null){
            //表示token 已经过期或者不正确
            return null;
        }else {
            return createJsonWebToken(authTokenDetails);
        }
    }

    /**
     * 设定过期时间
     * @return
     */
    private Date buildExpirationDate(int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }
}
