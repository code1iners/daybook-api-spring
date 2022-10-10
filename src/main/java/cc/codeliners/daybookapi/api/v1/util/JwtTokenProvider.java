package cc.codeliners.daybookapi.api.v1.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Date;

@Log4j2
@Component
public class JwtTokenProvider {

    private final UserDetailsService userDetailsService;

    // secret key
    private static final String JWT_SECRET_KEY = "dayBookJwtTokenSecretKey";

    // 토큰 만료시간
    private static final int JWT_EXPIRATION_TIME = 5*60*60*60;

    public JwtTokenProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // 이메일을 받아 토큰 만들기
    public String createToken(String email){
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role","ROLE_USER");
        // TODO role 추가할건가?
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + JWT_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256,JWT_SECRET_KEY)
                .compact();
    }

    // JWT 토큰을 이용하여 인증 정보를 조회하기
    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUserEmailFromToken(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰의 sub를 꺼내오기 (sub = email)
    public static String getUserEmailFromToken(String token){
        return Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJwt(token).getBody().getSubject();
    }

    public static boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJwt(token);
            return true;
        }catch (SignatureException e){
            log.error("SignatureException : "+e.getMessage());
        }catch (MalformedJwtException e){
            log.error("MalformedJwtException : "+e.getMessage());
        }catch (ExpiredJwtException e){
            log.error("ExpiredJwtException : "+e.getMessage());
        }catch (UnsupportedJwtException e){
            log.error("UnsupportedJwtException : "+e.getMessage());
        }catch (IllegalArgumentException e){
            log.error("IllegalArgumentException : "+e.getMessage());
        }
        return false;
    }

}
