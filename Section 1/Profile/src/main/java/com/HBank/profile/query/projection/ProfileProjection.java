package com.HBank.profile.query.projection;

import com.example.hbankbom.event.AccountDataChangedEvent;
import com.example.hbankbom.event.CustomerDataChangedEvent;
import com.example.hbankbom.event.LoanDataChangedEvent;
import com.example.hbankbom.event.CardDataChangedEvent;
import com.HBank.profile.service.IProfileService;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ProcessingGroup("customer-group")
public class ProfileProjection {

    private final IProfileService iProfileService;

    @EventHandler
    public void on(CustomerDataChangedEvent customerDataChangedEvent) {
        iProfileService.handleCustomerDataChangedEvent(customerDataChangedEvent);
    }

    @EventHandler
    public void on(AccountDataChangedEvent accountDataChangedEvent) {
        iProfileService.handleAccountDataChangedEvent(accountDataChangedEvent);
    }

    @EventHandler
    public void on(LoanDataChangedEvent loanDataChangedEvent) {
        iProfileService.handleLoanDataChangedEvent(loanDataChangedEvent);
    }

    @EventHandler
    public void on(CardDataChangedEvent customerDataChangedEvent) {
        iProfileService.handleCardDataChangedEvent(customerDataChangedEvent);
    }

}
