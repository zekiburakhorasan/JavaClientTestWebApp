/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.horasan.javaclienttestwebapp.util;

import org.springframework.http.HttpHeaders;
/**
 *
 * @author HORASAN
 */
public final class HttpUtils
{
    public HttpUtils()
    {
        throw new IllegalAccessError("Final Utility Class");
    }
    
    public static HttpHeaders generateAuthorizantionHeader(String authToken)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authToken);
        return headers;
    }
}
