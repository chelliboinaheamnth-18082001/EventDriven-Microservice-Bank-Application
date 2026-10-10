package com.HBank.customer.CQRS_EventSourcing.Command.Event;

import lombok.Data;

@Data
public class CustomerUpdatedEvent {

    private String customerId;
    private String name;
    private String email;
    private String mobileNumber;
    private boolean activeSw;

}