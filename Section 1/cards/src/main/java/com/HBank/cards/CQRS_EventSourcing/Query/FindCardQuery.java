package com.HBank.cards.CQRS_EventSourcing.Query;

import lombok.Value;

@Value
public class FindCardQuery {
    private final String mobileNumber;
}
