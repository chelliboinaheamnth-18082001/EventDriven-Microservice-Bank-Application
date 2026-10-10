package com.HBank.loans.service;

import com.HBank.loans.CQRS_EventSourcing.Command.event.LoanUpdatedEvent;
import com.HBank.loans.dto.LoansDto;
import com.HBank.loans.entity.Loans;

public interface ILoansService {

    /**
     * @param loan - Loans object
     */
    void createLoan(Loans loan);

    /**
     * @param mobileNumber - Input mobile Number
     * @return Loan Details based on a given mobileNumber
     */
    LoansDto fetchLoan(String mobileNumber);

    /**
     * @param event - LoanUpdatedEvent Object
     * @return boolean indicating if the update of card details is successful or not
     */
    boolean updateLoan(LoanUpdatedEvent event);

    /**
     * @param loanNumber - Input Loan Number
     * @return boolean indicating if the delete of loan details is successful or not
     */
    boolean deleteLoan(Long loanNumber);

}