package com.HBank.cards.CQRS_EventSourcing.Command.event;

import lombok.Data;

@Data
public class CardCreatedEvent {
    private Long cardNumber;
    private String mobileNumber;
    private String cardType;
    private int totalLimit;
    private int amountUsed;
    private int availableAmount;
    private boolean activeSw;
}
