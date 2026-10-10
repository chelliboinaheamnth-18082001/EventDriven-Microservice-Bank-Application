package com.HBank.loans.CQRS_EventSourcing.Query.Projection;


import com.HBank.loans.CQRS_EventSourcing.Command.event.LoanCreatedEvent;
import com.HBank.loans.CQRS_EventSourcing.Command.event.LoanDeletedEvent;
import com.HBank.loans.CQRS_EventSourcing.Command.event.LoanUpdatedEvent;
import com.HBank.loans.entity.Loans;
import com.HBank.loans.service.ILoansService;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ProcessingGroup("loan-group")
public class LoanProjection {

    private final ILoansService iLoansService;

    @EventHandler
    public void on(LoanCreatedEvent event) {
        Loans loanEntity = new Loans();
        BeanUtils.copyProperties(event, loanEntity);
        iLoansService.createLoan(loanEntity);
    }

    @EventHandler
    public void on(LoanUpdatedEvent event) {
        iLoansService.updateLoan(event);
    }

    @EventHandler
    public void on(LoanDeletedEvent event) {
        iLoansService.deleteLoan(event.getLoanNumber());
    }

}