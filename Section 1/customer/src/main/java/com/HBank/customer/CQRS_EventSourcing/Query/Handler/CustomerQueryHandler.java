package com.HBank.customer.CQRS_EventSourcing.Query.Handler;

import com.HBank.customer.CQRS_EventSourcing.Query.FindCustomerQuery;
import com.HBank.customer.dto.CustomerDto;
import com.HBank.customer.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerQueryHandler {

    private final ICustomerService iCustomerService;

    @QueryHandler
    public CustomerDto findCustomer(FindCustomerQuery findCustomerQuery) {
        return iCustomerService.fetchCustomer(findCustomerQuery.getMobileNumber());
    }

}