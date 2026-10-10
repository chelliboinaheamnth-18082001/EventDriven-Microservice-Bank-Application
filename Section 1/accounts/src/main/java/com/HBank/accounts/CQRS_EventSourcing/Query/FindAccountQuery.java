package com.HBank.accounts.CQRS_EventSourcing.Query;

import lombok.Value;

@Value
public class FindAccountQuery {
    private final String mobileNumber;
}