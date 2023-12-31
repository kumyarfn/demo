package com.komyar.demonstration.security.filter;

import com.komyar.demonstration.util.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.komyar.demonstration.enums.Security.OPTIONS_HTTP_METHOD;
import static com.komyar.demonstration.enums.Security.TOKEN_PREFIX;

@Component
@AllArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getMethod().equalsIgnoreCase(OPTIONS_HTTP_METHOD.getValue())) response.setStatus(HttpStatus.OK.value());
        else {
          String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
          if (authorizationHeader == null || !authorizationHeader.startsWith(TOKEN_PREFIX.getValue())){
              filterChain.doFilter(request, response);
              return;
          }
          String token = authorizationHeader.substring(TOKEN_PREFIX.getValue().length());
          String username = jwtTokenProvider.getSubject(token);
          if (jwtTokenProvider.isTokenValid(username,token))
              SecurityContextHolder.getContext().setAuthentication(jwtTokenProvider.getAuthentication(username, jwtTokenProvider.getAuthorities(token), request));
          else SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(request,response);
    }

}
