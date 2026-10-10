package com.HBank.cards.CQRS_EventSourcing.Command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class DeleteCardCommand {

    @TargetAggregateIdentifier
    private final Long cardNumber;
    private final boolean activeSw;

}
