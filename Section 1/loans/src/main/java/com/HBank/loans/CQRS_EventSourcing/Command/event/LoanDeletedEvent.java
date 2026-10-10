package com.HBank.loans.CQRS_EventSourcing.Command.event;

import lombok.Data;

@Data
public class LoanDeletedEvent {

    private Long loanNumber;
    private boolean activeSw;

}
