package com.HBank.loans.CQRS_EventSourcing.Query;

import lombok.Value;

@Value
public class FindLoanQuery {
    private final String mobileNumber;
}