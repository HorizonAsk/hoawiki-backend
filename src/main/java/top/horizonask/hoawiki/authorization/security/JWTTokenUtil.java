package top.horizonask.hoawiki.authorization.security;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import top.horizonask.hoawiki.authorization.security.services.UserDetailsImpl;

import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import java.util.*;

/**
 * @description:
 * @author: Yanbo Han
 * @time: 2021/12/31 0:34
 */

@Slf4j
public class JWTTokenUtil {

    private static final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

//    @Override
//    public Key resolveSigningKey(JwsHeader jwsHeader, Claims claims) {
//        String name = jwsHeader.get("name").toString();
//        String sub = claims.getSubject();
//
//        String keyId=jwsHeader.getKeyId();
//        // 使用keyId去获取保存在内存或者redis中的key。
//        return null;
//    }

    public static String createAccessToken(UserDetailsImpl userDetailsImpl) {
        String token = Jwts.builder().setId(
                        userDetailsImpl.getUserId().toString())
                .setSubject(userDetailsImpl.getEmail())
                .setIssuedAt(new Date())
                .setIssuer(JWTConfig.issuer)
                .setExpiration(new Date(System.currentTimeMillis() + JWTConfig.expiration))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .claim("permissions", userDetailsImpl.getAuthorities())
                .claim("isSuperAdmin", userDetailsImpl.getIsSuperAdmin())
                .compact();
        return JWTConfig.tokenPrefix + token;
    }

    public static Long getUserIdFromJwtToken(String token) {
        Claims claims = null;
        if (StringUtils.isNotEmpty(token)) {
            try {
                token = token.substring(JWTConfig.tokenPrefix.length());

                claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
                return Long.parseLong(claims.getId());
            } catch (Exception e) {
                log.error("Parse Token failed:" + e);
            }
        }
        return null;
    }

    public static String getUserEmailFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
    }

    public static UserDetailsImpl parseAccessToken(String token) {
        UserDetailsImpl userDetailsImpl = null;

        if (StringUtils.isNotEmpty(token)) {
            try {
                token = token.substring(JWTConfig.tokenPrefix.length());

                Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();

                log.info(claims.getSubject());

                userDetailsImpl = new UserDetailsImpl();
                userDetailsImpl.setUserId(Long.parseLong(claims.getId()));
                userDetailsImpl.setEmail(claims.getSubject());

                Set<GrantedAuthority> permissions = new HashSet<GrantedAuthority>();

                String permission = claims.get("permissions").toString();
                if (StringUtils.isNotEmpty(permission)) {
                    JSONArray permissionConverted = JSONUtil.parseArray(permission);
                    List<Map<String, String>> permissionList = Convert.convert(new TypeReference<List<Map<String, String>>>() {
                    }, permissionConverted);

                    for (Map<String, String> role : permissionList) {
                        if (!role.isEmpty()) {
                            permissions.add(new SimpleGrantedAuthority(role.get("authority")));
                        }
                    }
                }
                userDetailsImpl.setAuthorities(permissions);

                Boolean isSuperAdmin = (Boolean) claims.get("isSuperAdmin");
                userDetailsImpl.setIsSuperAdmin(isSuperAdmin);

            } catch (Exception e) {
                log.error("Parse Token failed:" + e);
            }
        }
        return userDetailsImpl;
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(authToken);
            return true;
        } catch (SecurityException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

}
