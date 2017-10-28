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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author HORASAN
 */
@Controller
@RequestMapping("/transaction")
public class TransactionController
{
    @Autowired
    private HttpSession httpSession;
    
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
}
