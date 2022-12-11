package cc.codeliners.daybookapi.api.v1.util;

import cc.codeliners.daybookapi.api.v1.entity.User;
import cc.codeliners.daybookapi.api.v1.service.UserDetailsServiceImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/*******************************************************************
 * 토큰을 생성하고 검증하는 클래스
 * 해당 컴포넌트는 필터클래스에서 사전 검증을 거친다
 *******************************************************************/
@Log4j2
@Component
public class JwtTokenProvider {

    private final UserDetailsServiceImpl userDetailsService;

    // secret key
    private static String secretKey = "djcnjsldiwsnsdjlksjdanshadsuwsdjksmandmskjdayBookJwtTokenSecretKeyByHyunjiAndWony";

    // 토큰 만료시간
    private static final int JWT_EXPIRATION_TIME = 5*60*60*60;

    public JwtTokenProvider(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // 객체 초기화, secretKey를 base64로 인코딩
//    @PostConstruct
//    protected void init() {
//        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
//    }

    // JWT 토큰 생성
    public String createToken(int userId, String email){
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("userId", userId);
        claims.put("roles","ROLE_USER");

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + JWT_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
    }

    // 헤더에서 토큰 추출
    public String getToken(HttpServletRequest request){
        return request.getHeader("token");
    }

    // JWT 토큰을 이용하여 인증 정보를 조회
    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUserEmailFromToken(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰의 sub를 꺼내오기 (sub = email)
    public String getUserEmailFromToken(String token){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token).getBody()
                .getSubject();
    }

    public int getUserIdByToken(String token){
        return (int) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("userId");
    }

    public boolean validateToken(String token){
        try{
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return claims.getBody().getExpiration().before(new Date());
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
