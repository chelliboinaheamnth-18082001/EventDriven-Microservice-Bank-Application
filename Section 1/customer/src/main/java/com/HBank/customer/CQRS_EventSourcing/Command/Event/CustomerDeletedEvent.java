package com.HBank.customer.CQRS_EventSourcing.Command.Event;

import lombok.Data;

@Data
public class CustomerDeletedEvent {

    private String customerId;
    private boolean activeSw;

}