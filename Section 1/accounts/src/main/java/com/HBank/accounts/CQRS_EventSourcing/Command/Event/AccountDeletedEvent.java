package com.HBank.accounts.CQRS_EventSourcing.Command.Event;

import lombok.Data;

@Data
public class AccountDeletedEvent {

    private Long accountNumber;
    private boolean activeSw;

}
