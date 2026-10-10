package com.HBank.accounts.CQRS_EventSourcing.Command.Event;

import lombok.Data;

@Data
public class AccountUpdatedEvent {

    private Long accountNumber;
    private String mobileNumber;
    private String accountType;
    private String branchAddress;
    private boolean activeSw;

}