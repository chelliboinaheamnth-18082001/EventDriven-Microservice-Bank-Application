package com.HBank.customer.CQRS_EventSourcing.Query.Projection;

import com.HBank.customer.CQRS_EventSourcing.Command.Event.CustomerCreatedEvent;
import com.HBank.customer.CQRS_EventSourcing.Command.Event.CustomerDeletedEvent;
import com.HBank.customer.CQRS_EventSourcing.Command.Event.CustomerUpdatedEvent;
import com.HBank.customer.entity.Customer;
import com.HBank.customer.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ProcessingGroup("customer-group")
public class CustomerProjection {

    private final ICustomerService iCustomerService;

    @EventHandler
    public void on(CustomerCreatedEvent customerCreatedEvent) {
        Customer customerEntity = new Customer();
        BeanUtils.copyProperties(customerCreatedEvent,customerEntity);
        iCustomerService.createCustomer(customerEntity);
    }

    @EventHandler
    public void on(CustomerUpdatedEvent customerUpdatedEvent) {
//       throw new RuntimeException("It is a bad day!!");
        iCustomerService.updateCustomer(customerUpdatedEvent);
    }

    @EventHandler
    public void on(CustomerDeletedEvent customerDeletedEvent) {
        iCustomerService.deleteCustomer(customerDeletedEvent.getCustomerId());
    }

}