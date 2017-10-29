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
public class Data
{
    private boolean captureAble;
    private boolean refundable;
    private String created_at;
    private String updated_at;
    private FX fx;
    private IPN ipn;
    private Merchant merchant;
    private CustomerInfo customerInfo;
    private Acquirer acquirer;
    private Transaction transaction;
}