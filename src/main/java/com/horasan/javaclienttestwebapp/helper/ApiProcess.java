/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.horasan.javaclienttestwebapp.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.horasan.javaclienttestwebapp.security.AuthenticationData;
import com.horasan.javaclienttestwebapp.util.HttpUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author HORASAN
 */
public class ApiProcess
{
    private final Properties properties = new Properties();
    private final RestTemplate restTemplate = new RestTemplate();
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    public ApiProcess()
    {
        try
        {
            File file = new File("src/main/resources/properties/api.properties");
            try(FileInputStream fileInputStream = new FileInputStream(file))
            {
                properties.load(fileInputStream);
            }
            catch (FileNotFoundException ex) {}
        }
        catch (IOException ex) {}
    }
    public LoginResponse getLoginResponse(LoginRequest loginRequest)
    {
        String url = properties.getProperty("MerchantLogin");
        LoginResponse loginResponse = new LoginResponse(0, "", "", "");
        try
        {
            loginResponse = restTemplate.exchange(url, 
                    HttpMethod.POST, new HttpEntity<>(loginRequest), 
                    LoginResponse.class).getBody();
        }
        catch (RestClientException e)
        {
            logger.info("RestClientException: " + e.getMessage());
        }
        return loginResponse;
    }
    public TransactionListResponse getTransactionListResponse (TransactionListRequest transactionListRequest, 
            HttpSession httpSession)
    {
        String url = properties.getProperty("TransactionQuery");
        logger.info("URL: " + url);
        AuthenticationData authenticationData = (AuthenticationData) httpSession.getAttribute("auth");
        logger.info("AuthenticationData.Token: " + authenticationData.getToken());
        TransactionListResponse transactionListResponse = new TransactionListResponse();
        try
        {
            transactionListResponse = restTemplate.exchange(url, HttpMethod.POST,
                            new HttpEntity<>(transactionListRequest,
                                    HttpUtils.generateAuthorizationHeader(authenticationData.getToken())),
                            TransactionListResponse.class).getBody();
        }
        catch (RestClientException e)
        {
            logger.info("RestClientException: " + e.getMessage());
        }
        int currentPage = transactionListResponse.getCurrent_page();
        while (transactionListResponse.getNext_page_url() != (Object) null)
        {
            transactionListRequest.setPage(Integer.toString(++currentPage));
            TransactionListResponse tempListResponse = new TransactionListResponse();
            try
            {
                tempListResponse = restTemplate.exchange(url, HttpMethod.POST,
                                new HttpEntity<>(transactionListRequest, 
                                HttpUtils.generateAuthorizationHeader(authenticationData.getToken())), 
                                TransactionListResponse.class).getBody();
            }
            catch (RestClientException e)
            {
                logger.info("RestClientException: " + e.getMessage());
            }
            transactionListResponse.getDataList().addAll(tempListResponse.getDataList());
            transactionListResponse.setNext_page_url(tempListResponse.getNext_page_url());
            transactionListResponse.setTo(transactionListResponse.getTo() 
                    + (tempListResponse.getTo() - tempListResponse.getFrom() + 1));
        }
        logger.info("TransactionListResponse.To: " + transactionListResponse.getTo());
        return transactionListResponse;
    }
    public TransactionReportResponse getTransactionReportResponse(
            TransactionReportRequest transactionReportRequest, 
            HttpSession httpSession)
    {
        String url = properties.getProperty("TransactionReport");
        logger.info("URL: " + url);
        AuthenticationData authenticationData = (AuthenticationData) httpSession.getAttribute("auth");
        logger.info("AuthenticationData.Token: " + authenticationData.getToken());
        TransactionReportResponse transactionReportResponse = new TransactionReportResponse();
        try
        {
            transactionReportResponse = restTemplate.exchange(url, HttpMethod.POST, 
                    new HttpEntity<>(transactionReportRequest, 
                            HttpUtils.generateAuthorizationHeader(authenticationData.getToken())), 
                    TransactionReportResponse.class).getBody();
        }
        catch (RestClientException e)
        {
            logger.info("RestClientException: " + e.getMessage());
        }
        try
        {
            logger.info("TransactionReportResponse: " + (new ObjectMapper())
                    .writeValueAsString(transactionReportResponse));
        }
        catch (JsonProcessingException ex)
        {
            logger.info("JsonProcessingException: " + ex.getMessage());
        }
        return transactionReportResponse;
    }
}
