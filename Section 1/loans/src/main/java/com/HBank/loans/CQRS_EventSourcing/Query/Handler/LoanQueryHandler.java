package com.HBank.loans.CQRS_EventSourcing.Query.Handler;


import com.HBank.loans.CQRS_EventSourcing.Query.FindLoanQuery;
import com.HBank.loans.dto.LoansDto;
import com.HBank.loans.service.ILoansService;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoanQueryHandler {

    private final ILoansService iLoansService;

    @QueryHandler
    public LoansDto findLoan(FindLoanQuery query) {
        LoansDto loan = iLoansService.fetchLoan(query.getMobileNumber());
        return loan;
    }

}