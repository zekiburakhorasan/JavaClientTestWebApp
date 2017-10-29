/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.horasan.javaclienttestwebapp.model.view;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author HORASAN
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionListView
{
    private String fromDate;
    private String toDate;
    private String status;
    private String operation;
    private String merchantId;
    private String acquirerId;
    private String paymentMethod;
    private String errorCode;
    private String filterField;
    private String filterValue;
    private String page;

    private Map<String, String> status​List;
    private Map<String, String> operation​List;
    private Map<String, String> paymentMethod​List;
    private Map<String, String> errorCode​List;
    private Map<String, String> filterField​List;


    public TransactionListView()
    {
        status​List = new HashMap<>();
        status​List.put("APPROVED", "APPROVED");
        status​List.put("WAITING", "WAITING");
        status​List.put("DECLINED", "DECLINED");
        status​List.put("ERROR", "ERROR");
        
        operation​List = new HashMap<>();
        operation​List.put("DIRECT", "DIRECT");
        operation​List.put("REFUND", "REFUND");
        operation​List.put("3D", "3D");
        operation​List.put("3DAUTH", "3DAUTH");
        operation​List.put("STORED", "STORED");
        
        paymentMethod​List = new HashMap<>();
        paymentMethod​List.put("CREDITCARD", "CREDITCARD");
        paymentMethod​List.put("CUP", "CUP");
        paymentMethod​List.put("IDEAL", "IDEAL");
        paymentMethod​List.put("GIROPAY", "GIROPAY");
        paymentMethod​List.put("MISTERCASH", "MISTERCASH");
        paymentMethod​List.put("STORED", "STORED");
        paymentMethod​List.put("PAYTOCARD", "PAYTOCARD");
        paymentMethod​List.put("CEPBANK", "CEPBANK");
        paymentMethod​List.put("CITADEL", "CITADEL");
        
        errorCode​List = new HashMap<>();
        errorCode​List.put("Do not honor", "Do not honor");
        errorCode​List.put("Invalid Transaction", "Invalid Transaction");
        errorCode​List.put("Invalid Card", "Invalid Card");
        errorCode​List.put("Not sufficient funds", "Not sufficient funds");
        errorCode​List.put("Incorrect PIN", "Incorrect PIN");
        errorCode​List.put("Invalid country association", "Invalid country association");
        errorCode​List.put("Currency not allowed", "Currency not allowed");
        errorCode​List.put("3-D Secure Transport Error", "3-D Secure Transport Error");
        errorCode​List.put("Transaction not permitted to cardholder", "Transaction not permitted to cardholder");
        
        filterField​List = new HashMap<>();
        filterField​List.put("Transaction UUID", "Transaction UUID");
        filterField​List.put("Customer Email", "Customer Email");
        filterField​List.put("Reference No", "Reference No");
        filterField​List.put("Custom Data", "Custom Data");
        filterField​List.put("Card PAN", "Card PAN");
    }
}
