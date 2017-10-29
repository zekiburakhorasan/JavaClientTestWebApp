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
import java.util.Collection;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author HORASAN
 */
public class CustomAuthentication implements Authentication
{
    private final HttpSession httpSession;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private boolean authenticated;
    private final AuthenticationData authenticationData;
    private final Collection<? extends GrantedAuthority> authorities;
    private final Object credentials;
    private final Object details;
    private final Object principal;
    private final String name;

    public CustomAuthentication(AuthenticationData authenticationData, 
            Collection<? extends GrantedAuthority> authorities, 
            Object credentials, Object details, 
            HttpSession httpSession, 
            Object principal, 
            String name)
    {
        
        this.authenticationData = authenticationData;
        this.authorities = authorities;
        this.credentials = credentials;
        this.details = details;
        this.httpSession = httpSession;
        this.principal = principal;
        this.name = name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return authorities;
    }

    @Override
    public Object getCredentials()
    {
        return credentials;
    }

    @Override
    public Object getDetails()
    {
        return details;
    }

    @Override
    public Object getPrincipal()
    {
        return principal;
    }

    @Override
    public boolean isAuthenticated()
    {
        if (LocalDateTime.now().isBefore(authenticationData.getExpireTime()))
        {
            logger.info("if (LocalDateTime.now().isBefore(authenticationData.getExpireTime())) {");
            setAuthenticated(true);
            return true;
        }
        else if (LocalDateTime.now().isAfter(authenticationData.getExpireTime()))
        {
            LoginRequest loginRequest = new LoginRequest(name, credentials.toString());
            LoginResponse loginResponse = new ApiProcess().getLoginResponse(loginRequest);
            if (loginResponse.getStatus().equals("APPROVED"))
            {
                logger.info("if (loginResponse.getStatus().equals(\"APPROVED\")) {");
                authenticationData.setExpireTime(LocalDateTime.now().plusMinutes(10));
                authenticationData.setToken(loginResponse.getToken());
                httpSession.setAttribute("auth", authenticationData);
                return true;
            }
        }
        logger.info("Authenticated: false");
        setAuthenticated(false);
        return false;
    }

    @Override
    public void setAuthenticated(boolean bln) throws IllegalArgumentException
    {
        authenticated = bln;
    }

    @Override
    public String getName()
    {
        return name;
    }
}
