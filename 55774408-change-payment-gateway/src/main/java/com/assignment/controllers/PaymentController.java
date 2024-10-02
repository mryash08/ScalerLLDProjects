package com.assignment.controllers;


import com.assignment.dtos.MakePaymentRequestDto;
import com.assignment.dtos.MakePaymentResponseDto;
import com.assignment.dtos.ResponseStatus;
import com.assignment.exceptions.InvalidBillException;
import com.assignment.models.Payment;
import com.assignment.models.PaymentStatus;
import com.assignment.services.PaymentService;

public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public MakePaymentResponseDto makePayment(MakePaymentRequestDto makePaymentRequestDto) {
        MakePaymentResponseDto dto = new MakePaymentResponseDto();
        try {
            Payment payment = paymentService.makePayment(makePaymentRequestDto.getBillId());
            dto.setPaymentStatus(PaymentStatus.SUCCESS);
            dto.setResponseStatus(ResponseStatus.SUCCESS);
            dto.setTxnId(payment.getTxnId());
        } catch (InvalidBillException e) {
            dto.setResponseStatus(ResponseStatus.FAILURE);
        }

        
        return dto;
    }
}