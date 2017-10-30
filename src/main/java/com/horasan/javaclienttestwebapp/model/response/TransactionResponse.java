/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.horasan.javaclienttestwebapp.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.horasan.javaclienttestwebapp.innerclasses.Acquirer;
import com.horasan.javaclienttestwebapp.innerclasses.CustomerInfo;
import com.horasan.javaclienttestwebapp.innerclasses.FX;
import com.horasan.javaclienttestwebapp.innerclasses.Merchant;
import com.horasan.javaclienttestwebapp.innerclasses.TransactionMerchant;
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
public class TransactionResponse
{
    private FX fx;
    private CustomerInfo customerInfo;
    private Merchant merchant;
    private TransactionMerchant transactionMerchant;
    private Acquirer acquirer;
}
