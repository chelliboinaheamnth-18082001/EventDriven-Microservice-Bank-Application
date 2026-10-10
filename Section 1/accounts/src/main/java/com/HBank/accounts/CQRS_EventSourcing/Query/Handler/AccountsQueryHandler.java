package com.HBank.accounts.CQRS_EventSourcing.Query.Handler;


import com.HBank.accounts.CQRS_EventSourcing.Query.FindAccountQuery;
import com.HBank.accounts.dto.AccountsDto;
import com.HBank.accounts.service.IAccountsService;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountsQueryHandler {

    private final IAccountsService iAccountsService;

    @QueryHandler
    public AccountsDto findAccount(FindAccountQuery query) {
        AccountsDto account = iAccountsService.fetchAccount(query.getMobileNumber());
        return account;
    }

}