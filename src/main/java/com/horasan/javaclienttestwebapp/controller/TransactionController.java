/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.horasan.javaclienttestwebapp.controller;

import com.horasan.javaclienttestwebapp.security.AuthenticationData;
import com.horasan.javaclienttestwebapp.helper.ApiProcess;
import com.horasan.javaclienttestwebapp.model.request.TransactionListRequest;
import com.horasan.javaclienttestwebapp.model.request.TransactionReportRequest;
import com.horasan.javaclienttestwebapp.model.response.TransactionListResponse;
import com.horasan.javaclienttestwebapp.model.response.TransactionReportResponse;
import com.horasan.javaclienttestwebapp.model.view.TransactionListView;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    @GetMapping(path = "/report")
    public String getReport(TransactionReportRequest transactionReportRequest)
    {
        return "transactionReport";
    }
    
    @PostMapping(path = "/report")
    public String postReport(@Valid TransactionReportRequest transactionReportRequest, 
            BindingResult bindingResult,
            Model model)
    {
        TransactionReportResponse transactionReportResponse = new ApiProcess()
                .getTransactionReportResponse(transactionReportRequest, httpSession);
        model.addAttribute("transactionReportResponse", transactionReportResponse);
        return "transactionReport";
    }
    
    @GetMapping(path = "/list")
    public String getList(TransactionListView transactionListView)
    {
        return "transactionList";
    }
    
    @PostMapping(path = "/list")
    public String postList(@Valid TransactionListView transactionListView,
            BindingResult bindingResult,
            Model model)
    {
        TransactionListRequest transactionListRequest
                = new TransactionListRequest(transactionListView.getFromDate(),
                        transactionListView.getToDate(),
                        transactionListView.getStatus(),
                        transactionListView.getOperation(),
                        transactionListView.getMerchantId(),
                        transactionListView.getAcquirerId(),
                        transactionListView.getPaymentMethod(),
                        transactionListView.getErrorCode(),
                        transactionListView.getFilterField(),
                        transactionListView.getFilterValue(),
                        transactionListView.getPage());
        TransactionListResponse transactionListResponse = new ApiProcess()
                .getTransactionListResponse(transactionListRequest, httpSession);
        model.addAttribute("transactionListResponse", transactionListResponse);
        return "transactionList";
    }
}
