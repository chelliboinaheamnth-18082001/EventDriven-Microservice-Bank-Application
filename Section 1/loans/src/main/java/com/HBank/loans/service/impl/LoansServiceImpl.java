package com.HBank.loans.service.impl;

import com.HBank.loans.CQRS_EventSourcing.Command.event.LoanUpdatedEvent;
import com.HBank.loans.constants.LoansConstants;
import com.HBank.loans.dto.LoansDto;
import com.HBank.loans.entity.Loans;
import com.HBank.loans.exception.LoanAlreadyExistsException;
import com.HBank.loans.exception.ResourceNotFoundException;
import com.HBank.loans.mapper.LoansMapper;
import com.HBank.loans.repository.LoansRepository;
import com.HBank.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {

    private LoansRepository loansRepository;

    /**
     * @param loan - Loans object
     */
    @Override
    public void createLoan(Loans loan) {
        Optional<Loans> optionalLoans = loansRepository.findByMobileNumberAndActiveSw(loan.getMobileNumber(),
                LoansConstants.ACTIVE_SW);
        if (optionalLoans.isPresent()) {
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber " + loan.getMobileNumber());
        }
        loansRepository.save(loan);
    }

    /**
     * @param mobileNumber - Input mobile Number
     * @return Loan Details based on a given mobileNumber
     */
    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loan = loansRepository.findByMobileNumberAndActiveSw(mobileNumber, LoansConstants.ACTIVE_SW)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
                );
        LoansDto loansDto = LoansMapper.mapToLoansDto(loan, new LoansDto());
        return loansDto;
    }

    /**
     * @param event - LoanUpdatedEvent Object
     * @return boolean indicating if the update of loan details is successful or not
     */
    @Override
    public boolean updateLoan(LoanUpdatedEvent event) {
        Loans loan = loansRepository.findByMobileNumberAndActiveSw(event.getMobileNumber(),
                LoansConstants.ACTIVE_SW).orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber",
                event.getMobileNumber()));
        LoansMapper.mapEventToLoan(event, loan);
        loansRepository.save(loan);
        return true;
    }

    /**
     * @param loanNumber - Input Loan Number
     * @return boolean indicating if the delete of loan details is successful or not
     */
    @Override
    public boolean deleteLoan(Long loanNumber) {
        Loans loan = loansRepository.findById(loanNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "loanNumber", loanNumber.toString())
        );
        loan.setActiveSw(LoansConstants.IN_ACTIVE_SW);
        loansRepository.save(loan);
        return true;
    }


}