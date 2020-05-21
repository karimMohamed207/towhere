package com.karim.fillter;

import com.karim.security.TokenUtil;
import com.karim.security.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter extends OncePerRequestFilter {

    private String TOKEN_HEADER = "Authorization";
    @Autowired
    private TokenUtil tokenUtil ;
    @Autowired
    private UserDetail service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //taken men al header
        final String header =  request.getHeader(TOKEN_HEADER);
        final SecurityContext securityContext = SecurityContextHolder.getContext();
        if(header != null && securityContext.getAuthentication() == null){
            String token = header.substring("Bearer".length());
            String userame = tokenUtil.getUserNameFromToken(token);
            if(userame != null){
                UserDetails userDetail = service.loadUserByUsername(userame);
                if(tokenUtil.isTokenValid(token , userDetail)){
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetail , null ,  userDetail.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request,response);
        //make sure is valid
    }
}
