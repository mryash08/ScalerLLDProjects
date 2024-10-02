package com.assignment.services;

import java.util.Optional;

import com.assignment.adapters.PaymentGatewayAdapter;
import com.assignment.exceptions.InvalidBillException;
import com.assignment.models.Bill;
import com.assignment.models.Payment;
import com.assignment.repositories.BillRepoimpl;

public class PaymentServiceimpl implements PaymentService {
    BillRepoimpl billRepoimpl;
    PaymentGatewayAdapter paymentGatewayAdapter;

        public PaymentServiceimpl(BillRepoimpl billRepoimpl,PaymentGatewayAdapter paymentGatewayAdapter){
            this.billRepoimpl = billRepoimpl;
            this.paymentGatewayAdapter = paymentGatewayAdapter;
        }

    @Override
    public Payment makePayment(long billId) throws InvalidBillException {
        
        Bill bill = billRepoimpl.findById(billId).orElseThrow(() -> new InvalidBillException("Bill not found"));
        return paymentGatewayAdapter.makePayment(billId, bill.getTotalAmount());

    }
    
}
