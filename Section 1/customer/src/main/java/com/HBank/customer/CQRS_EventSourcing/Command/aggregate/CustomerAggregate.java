package com.HBank.customer.CQRS_EventSourcing.Command.aggregate;


import com.HBank.customer.CQRS_EventSourcing.Command.CreateCustomerCommand;
import com.HBank.customer.CQRS_EventSourcing.Command.DeleteCustomerCommand;
import com.HBank.customer.CQRS_EventSourcing.Command.Event.CustomerCreatedEvent;
import com.HBank.customer.CQRS_EventSourcing.Command.Event.CustomerDeletedEvent;
import com.HBank.customer.CQRS_EventSourcing.Command.Event.CustomerUpdatedEvent;
import com.HBank.customer.CQRS_EventSourcing.Command.UpdateCustomerCommand;

import com.HBank.customer.repository.CustomerRepository;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;
import com.example.hbankbom.event.CustomerDataChangedEvent;
@Aggregate
public class CustomerAggregate {

    @AggregateIdentifier
    private String customerId;
    private String name;
    private String email;
    private String mobileNumber;
    private boolean activeSw;

    public CustomerAggregate() {

    }

    @CommandHandler
    public CustomerAggregate(CreateCustomerCommand createCustomerCommand, CustomerRepository customerRepository) {
        CustomerCreatedEvent customerCreatedEvent = new CustomerCreatedEvent();
        BeanUtils.copyProperties(createCustomerCommand, customerCreatedEvent);
        CustomerDataChangedEvent customerDataChangedEvent = new CustomerDataChangedEvent();
        BeanUtils.copyProperties(createCustomerCommand, customerDataChangedEvent);
        System.out.println(
                "Publishing CustomerDataChangedEvent: mobileNumber = "
                        + customerDataChangedEvent.getMobileNumber()
        );

        AggregateLifecycle.apply(customerCreatedEvent) ;

        AggregateLifecycle.apply(customerDataChangedEvent);
    }

    @EventSourcingHandler
    public void on(CustomerCreatedEvent customerCreatedEvent) {
        this.customerId = customerCreatedEvent.getCustomerId();
        this.name = customerCreatedEvent.getName();
        this.email= customerCreatedEvent.getEmail();
        this.mobileNumber = customerCreatedEvent.getMobileNumber();
        this.activeSw = customerCreatedEvent.isActiveSw();
    }

    @CommandHandler
    public void handle(UpdateCustomerCommand updateCustomerCommand, EventStore eventStore) {
        CustomerUpdatedEvent customerUpdatedEvent = new CustomerUpdatedEvent();
        BeanUtils.copyProperties(updateCustomerCommand, customerUpdatedEvent);
        CustomerDataChangedEvent customerDataChangedEvent = new CustomerDataChangedEvent();
        BeanUtils.copyProperties(updateCustomerCommand, customerDataChangedEvent);
        AggregateLifecycle.apply(customerUpdatedEvent);
        AggregateLifecycle.apply(customerDataChangedEvent);
    }

    @EventSourcingHandler
    public void on(CustomerUpdatedEvent customerUpdatedEvent) {
        this.name = customerUpdatedEvent.getName();
        this.email= customerUpdatedEvent.getEmail();
    }

    @CommandHandler
    public void handle(DeleteCustomerCommand deleteCustomerCommand) {
        CustomerDeletedEvent customerDeletedEvent = new CustomerDeletedEvent();
        BeanUtils.copyProperties(deleteCustomerCommand, customerDeletedEvent);
        AggregateLifecycle.apply(customerDeletedEvent);
    }

    @EventSourcingHandler
    public void on(CustomerDeletedEvent customerDeletedEvent) {
        this.activeSw = customerDeletedEvent.isActiveSw();
    }

}