package com.HBank.accounts.CQRS_EventSourcing.Command.Event;

import lombok.Data;

@Data
public class AccountCreatedEvent {
    private Long accountNumber;
    private String mobileNumber;
    private String accountType;
    private String branchAddress;
    private boolean activeSw;
}