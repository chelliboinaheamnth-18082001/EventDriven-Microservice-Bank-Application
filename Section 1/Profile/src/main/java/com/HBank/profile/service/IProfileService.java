package com.HBank.profile.service;


import com.HBank.profile.dto.ProfileDto;
import com.example.hbankbom.event.AccountDataChangedEvent;
import com.example.hbankbom.event.CustomerDataChangedEvent;
import com.example.hbankbom.event.LoanDataChangedEvent;
import com.example.hbankbom.event.CardDataChangedEvent;

public interface IProfileService {

    /**
     * @param customerDataChangedEvent - CustomerDataChangedEvent Object
     */
    void handleCustomerDataChangedEvent(CustomerDataChangedEvent customerDataChangedEvent);

    /**
     * @param accountDataChangedEvent - AccountDataChangedEvent Object
     */
    void handleAccountDataChangedEvent(AccountDataChangedEvent accountDataChangedEvent);

    /**
     * @param loanDataChangedEvent - LoanDataChangedEvent Object
     */
    void handleLoanDataChangedEvent(LoanDataChangedEvent loanDataChangedEvent);

    /**
     * @param customerDataChangedEvent - CardDataChangedEvent Object
     */
    void handleCardDataChangedEvent(CardDataChangedEvent customerDataChangedEvent);

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Profile Details based on a given mobileNumber
     */
    ProfileDto fetchProfile(String mobileNumber);
}
