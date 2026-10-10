package com.HBank.customer.CQRS_EventSourcing.Command.aggregate;


import com.HBank.customer.CQRS_EventSourcing.Command.CreateCustomerCommand;
import com.HBank.customer.CQRS_EventSourcing.Command.DeleteCustomerCommand;
import com.HBank.customer.CQRS_EventSourcing.Command.Event.CustomerCreatedEvent;
import com.HBank.customer.CQRS_EventSourcing.Command.Event.CustomerDeletedEvent;
import com.HBank.customer.CQRS_EventSourcing.Command.Event.CustomerUpdatedEvent;
import com.HBank.customer.CQRS_EventSourcing.Command.UpdateCustomerCommand;
import com.HBank.customer.entity.Customer;
import com.HBank.customer.exception.CustomerAlreadyExistsException;
import com.HBank.customer.repository.CustomerRepository;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

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
    public CustomerAggregate(CreateCustomerCommand createCustomerCommand) {
//        Optional<Customer> optionalCustomer = customerRepository.
//                findByMobileNumberAndActiveSw(createCustomerCommand.getMobileNumber(), true);
//        if (optionalCustomer.isPresent()) {
//            throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber "
//                    + createCustomerCommand.getMobileNumber());
//        }
        CustomerCreatedEvent customerCreatedEvent = new CustomerCreatedEvent();
        BeanUtils.copyProperties(createCustomerCommand, customerCreatedEvent);
        AggregateLifecycle.apply(customerCreatedEvent);
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
    public void handle(UpdateCustomerCommand updateCustomerCommand) {
        /*List<?>   commands = eventStore.readEvents(updateCustomerCommand.getCustomerId()).asStream().toList();
        if(commands.isEmpty()) {
            throw new ResourceNotFoundException("Customer", "customerId", updateCustomerCommand.getCustomerId());
        }*/
        CustomerUpdatedEvent customerUpdatedEvent = new CustomerUpdatedEvent();
        BeanUtils.copyProperties(updateCustomerCommand, customerUpdatedEvent);
        AggregateLifecycle.apply(customerUpdatedEvent);
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