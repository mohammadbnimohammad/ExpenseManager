package com.mohammad.ExpenseManager.security;

import com.mohammad.ExpenseManager.service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
private final JwtUtil jwtUtil;
private final UserDetailsServiceImpl userDetailsService;

public JwtAuthenticationFilter(JwtUtil jwtUtil,UserDetailsServiceImpl userDetailsService){
    this.jwtUtil=jwtUtil;
    this.userDetailsService=userDetailsService;
}
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
    final String authHeader=request.getHeader("Authorization");
    String token = null;
    String email =null;

    if (authHeader!=null&&authHeader.startsWith("Bearer ")){
        token =authHeader.substring(7);
        email =jwtUtil.extractEmail(token);
    }
    if (email != null && SecurityContextHolder.getContext().getAuthentication() == null){
        var userDetails =userDetailsService.loadUserByUsername(email);
        if (jwtUtil.isValidateToken(token,userDetails)){
            var authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);

        }
    }
    filterChain.doFilter(request, response);
}

}
