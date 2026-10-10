package com.HBank.customer.CQRS_EventSourcing.Command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * VERB+NOUN+Command
 */
@Data
@Builder
public class DeleteCustomerCommand {

    @TargetAggregateIdentifier
    private final String customerId;
    private final boolean activeSw;

}