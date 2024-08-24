package com.example.simple.saga.dto;

import com.example.simple.saga.steps.account.service.Accounts;
import com.example.simple.saga.steps.customer.service.Customer;
import com.example.simple.saga.steps.payments.service.Payments;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    Customer customer;
    Payments payments;
    Accounts account;

}
