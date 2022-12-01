package com.xux.domain.customer.gateway;

import com.xux.domain.customer.Customer;

public interface CustomerGateway {
    Customer getByById(String customerId);
}
