package com.HBank.customer.CQRS_EventSourcing.Command.Event;

import lombok.Data;

/**
 * NOUN+VERB(PastTense)+Event
 */
@Data
public class CustomerCreatedEvent {

    private String customerId;
    private String name;
    private String email;
    private String mobileNumber;
    private boolean activeSw;


}