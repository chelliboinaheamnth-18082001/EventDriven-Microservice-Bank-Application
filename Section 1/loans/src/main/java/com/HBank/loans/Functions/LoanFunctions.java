package com.HBank.loans.Functions;

import com.HBank.loans.service.ILoansService;
import com.example.hbankbom.dto.MobileNumberUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class LoanFunctions {

    @Bean
    public Consumer<MobileNumberUpdateDto> updateLoanMobileNumber(ILoansService loansService)
    {
        return (mobileNumberUpdateDto) -> {
            log.info("Received  updateCardMobileNumber request  for the details: {}", mobileNumberUpdateDto);
            loansService.updateMobileNumber(mobileNumberUpdateDto);
        };
    }
}
