/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.horasan.javaclienttestwebapp.innerclasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author HORASAN
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class IPNMerchant
{
    private int date;
    private int amount;
    private int convertedAmount;
    private String ipnType;
    private String code;
    private String referenceNo;
    private String customData;
    private String descriptor;
    private String message;
    private String type;
    private String transactionId;
    private String paymentType;
    private String token;
    private String chainId;
    private String IPNUrl;
    private String authTransactionId;
    private String currency;
    private String operation;
    private String status;
    private String convertedCurrency;
}