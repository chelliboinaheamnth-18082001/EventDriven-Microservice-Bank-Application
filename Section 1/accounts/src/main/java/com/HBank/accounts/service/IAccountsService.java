package com.HBank.accounts.service;


import com.HBank.accounts.CQRS_EventSourcing.Command.Event.AccountUpdatedEvent;
import com.HBank.accounts.dto.AccountsDto;
import com.HBank.accounts.entity.Accounts;

public interface IAccountsService {

    /**
     *
     * @param account - Accounts Object
     */
    void createAccount(Accounts account);

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return Accounts Details based on a given mobileNumber
     */
    AccountsDto fetchAccount(String mobileNumber);

    /**
     *
     * @param event - AccountUpdatedEvent Object
     * @return boolean indicating if the update of Account details is successful or not
     */
    boolean updateAccount(AccountUpdatedEvent event);

    /**
     *
     * @param accountNumber - Input Account Number
     * @return boolean indicating if the delete of Account details is successful or not
     */
    boolean deleteAccount(Long accountNumber);


}