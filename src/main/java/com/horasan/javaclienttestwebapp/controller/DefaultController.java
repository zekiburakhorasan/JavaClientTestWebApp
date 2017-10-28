/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.horasan.javaclienttestwebapp.controller;

import com.horasan.javaclienttestwebapp.security.AuthenticationData;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author HORASAN
 */
@Controller
public class DefaultController extends WebMvcConfigurerAdapter
{
    @Autowired
    private HttpSession httpSession;
    public void addViewController(ViewControllerRegistry registry)
    {
        registry.addViewController("/").setViewName("index");
        super.addViewControllers(registry);
    }
    @ModelAttribute("email")
    public String getEmail()
    {
        AuthenticationData authenticationData = (AuthenticationData) httpSession.getAttribute("auth");
        if (authenticationData != null)
        {
            return authenticationData.getEmail();
        }
        return "";
    }
    @GetMapping(path = "/")
    public String getIndex() {
        return "index";
    }

    @GetMapping(path = "/login")
    public String getLogin() {
        return "login";
    }
}
