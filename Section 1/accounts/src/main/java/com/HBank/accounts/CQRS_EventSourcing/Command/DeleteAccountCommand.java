package com.HBank.accounts.CQRS_EventSourcing.Command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class DeleteAccountCommand {

    @TargetAggregateIdentifier
    private final Long accountNumber;
    private final boolean activeSw;

}