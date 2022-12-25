package com.letsgo.blog.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 1. get Token
        String requestToken = request.getHeader("Authorization"); // Bearer 2323434535464
        System.out.println(requestToken);

        String userName = null;
        String actualToken = null;

        if(requestToken !=null && requestToken.startsWith("Bearer")) {
            actualToken = requestToken.substring(7);
            try {
                userName = this.jwtTokenHelper.getUsernameFromToken(actualToken);
            } catch(IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("Token is expired");
            } catch (MalformedJwtException e) {
                System.out.println("Invalid jwt");
            }
        } else {
            System.out.println("Token does not start with Bearer");
        }

        //once we got the token
        //Now validate the token
        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
            if(this.jwtTokenHelper.validateToken(actualToken,userDetails)) {
                //token is valid
                // authenticate the user
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            } else {
                System.out.println("Invalid Jwt Token");
            }

        } else {
            System.out.println("User name is null or context is not null");
        }

        filterChain.doFilter(request,response);
    }
}
