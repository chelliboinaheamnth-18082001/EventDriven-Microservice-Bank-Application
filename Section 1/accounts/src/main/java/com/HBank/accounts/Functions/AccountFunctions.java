package com.HBank.accounts.Functions;

import com.HBank.accounts.service.IAccountsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.hbankbom.dto.MobileNumberUpdateDto;
import java.util.function.Consumer;

@Configuration
@Slf4j
public class AccountFunctions {

    @Bean
    public Consumer<MobileNumberUpdateDto> updateAccountMobileNumber(IAccountsService accountsService)
    {
        return (mobileNumberUpdateDto) -> {
            log.info("Received  updateAccountMobileNumber request  for the details: {}", mobileNumberUpdateDto);
            accountsService.updateMobileNumber(mobileNumberUpdateDto);
        };
    }

    @Bean
    public Consumer<MobileNumberUpdateDto> rollbackAccountMobileNumber(IAccountsService iAccountsService) {
        return (mobileNumberUpdateDto) -> {
            log.info("Received  rollbackAccountMobileNumber request  for the details: {}", mobileNumberUpdateDto);
            iAccountsService.rollbackMobileNumber(mobileNumberUpdateDto);
        };
    }
}
