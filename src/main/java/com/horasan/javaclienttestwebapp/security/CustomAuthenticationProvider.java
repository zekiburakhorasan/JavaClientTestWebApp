/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.horasan.javaclienttestwebapp.security;

import com.horasan.javaclienttestwebapp.helper.ApiProcess;
import com.horasan.javaclienttestwebapp.model.request.LoginRequest;
import com.horasan.javaclienttestwebapp.model.response.LoginResponse;
import java.time.LocalDateTime;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 *
 * @author HORASAN
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider
{
    @Autowired
    private HttpSession httpSession;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        logger.info("email = " + email + ", password = " + password);

        LoginRequest loginRequest = new LoginRequest(email, password);
        LoginResponse loginResponse = new ApiProcess().getLoginResponse(loginRequest);

        if (loginResponse.getStatus().equals("APPROVED"))
        {
            AuthenticationData authenticationData
                    = new AuthenticationData(email, password,
                            loginResponse.getToken(),
                            LocalDateTime.now().plusMinutes(10));
            httpSession.setAttribute("auth", authenticationData);
            CustomAuthentication customAuthentication
                    = new CustomAuthentication(authenticationData,
                            authentication.getAuthorities(),
                            authentication.getCredentials(),
                            authentication.getDetails(),
                            httpSession,
                            authentication.getPrincipal(),
                            authentication.getName());
            return customAuthentication;
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication)
    {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
