package com.HBank.cards.CQRS_EventSourcing.Command.event;

import lombok.Data;

@Data
public class CardDeletedEvent {

    private Long cardNumber;
    private boolean activeSw;

}
