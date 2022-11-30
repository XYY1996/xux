package com.xux.domain.customer.gateway;

import com.xux.domain.customer.Customer;
import com.xux.domain.customer.Credit;

//Assume that the credit info is in antoher distributed Service
public interface CreditGateway {
    Credit getCredit(String customerId);
}
