package com.HBank.accounts.CQRS_EventSourcing.Query.Projection;


import com.HBank.accounts.CQRS_EventSourcing.Command.Event.AccountCreatedEvent;
import com.HBank.accounts.CQRS_EventSourcing.Command.Event.AccountDeletedEvent;
import com.HBank.accounts.CQRS_EventSourcing.Command.Event.AccountUpdatedEvent;
import com.HBank.accounts.entity.Accounts;
import com.HBank.accounts.service.IAccountsService;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ProcessingGroup("account-group")
public class AccountProjection {

    private final IAccountsService iAccountsService;

    @EventHandler
    public void on(AccountCreatedEvent event) {
        Accounts accountEntity = new Accounts();
        BeanUtils.copyProperties(event, accountEntity);
        iAccountsService.createAccount(accountEntity);
    }

    @EventHandler
    public void on(AccountUpdatedEvent event) {
        iAccountsService.updateAccount(event);
    }

    @EventHandler
    public void on(AccountDeletedEvent event) {
        iAccountsService.deleteAccount(event.getAccountNumber());
    }

}