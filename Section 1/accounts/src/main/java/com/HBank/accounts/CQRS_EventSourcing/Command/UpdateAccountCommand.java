package com.HBank.accounts.CQRS_EventSourcing.Command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class UpdateAccountCommand {

    @TargetAggregateIdentifier
    private final Long accountNumber;
    private final String mobileNumber;
    private final String accountType;
    private final String branchAddress;
    private final boolean activeSw;

}