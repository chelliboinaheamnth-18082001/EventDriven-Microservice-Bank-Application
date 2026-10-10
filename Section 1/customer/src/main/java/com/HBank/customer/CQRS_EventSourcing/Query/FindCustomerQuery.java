package com.HBank.customer.CQRS_EventSourcing.Query;

import lombok.Value;

/**
 * VERB+NOUN+Query
 */
@Value
public class FindCustomerQuery {

    private final String mobileNumber;

}