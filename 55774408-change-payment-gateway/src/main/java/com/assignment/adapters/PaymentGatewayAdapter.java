package com.assignment.adapters;

import com.assignment.models.Payment;

public interface PaymentGatewayAdapter {

    Payment makePayment(long billId, double amount);
}