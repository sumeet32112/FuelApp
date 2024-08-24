package com.noxfilers.fuelApp.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noxfilers.fuelApp.services.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);
    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private CustomUserDetailsService customUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //Authorization


        //  hasAuthorizationBearer
        String requestHeader = request.getHeader("Authorization");
        logger.info(" Header :  {}", requestHeader);
        String username = null;
        String token = null;
        if (requestHeader != null ) {
            //looking good
            token = requestHeader.substring(7);
            try {

                username = this.jwtHelper.getUsernameFromToken(token);

            } catch (IllegalArgumentException e) {
                logger.info("Illegal Argument while fetching the username !!");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();

            }
        } else {
            logger.info("Invalid Header Value !! ");
        }

        //validate token and set authentication context
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            //fetch user detail from username
            UserDetails userDetails = this.customUserDetailService.loadUserByUsername(username);
            Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
            if (validateToken) {

                //set the authentication
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } else {
                logger.info("Validation fails !!");
            }
        }

        filterChain.doFilter(request, response);
    }
}
