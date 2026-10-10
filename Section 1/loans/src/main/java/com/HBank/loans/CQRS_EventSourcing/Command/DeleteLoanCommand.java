package com.HBank.loans.CQRS_EventSourcing.Command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class DeleteLoanCommand {

    @TargetAggregateIdentifier
    private final Long loanNumber;
    private final boolean activeSw;

}
