package cc.codeliners.daybookapi.api.v1.common;

import cc.codeliners.daybookapi.api.v1.util.JwtTokenProvider;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String token = request.getHeader("token");
            if(!token.isEmpty() && JwtTokenProvider.validateToken(token)){
                String userEmail = JwtTokenProvider.getUserEmailFromToken(token);

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userEmail,null);

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            else {
                log.error("토큰 에러");
            }
        } catch (Exception e){
            log.info(e.getMessage());
        }

        filterChain.doFilter(request,response);
    }
}
