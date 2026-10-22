package com.HBank.cards.Functions;

import com.HBank.cards.service.ICardsService;
import com.example.hbankbom.dto.MobileNumberUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class CardFunctions {

    @Bean
    public Consumer<MobileNumberUpdateDto> updateCardMobileNumber(ICardsService cardsService)
    {
        return (mobileNumberUpdateDto) -> {
            log.info("Received  updateCardMobileNumber request  for the details: {}", mobileNumberUpdateDto);
            cardsService.updateMobileNumber(mobileNumberUpdateDto);
        };
    }

    @Bean
    public Consumer<MobileNumberUpdateDto> rollbackCardMobileNumber(ICardsService iCardsService) {
        return (mobileNumberUpdateDto) -> {
            log.info("Received  rollbackCardMobileNumber request  for the details: {}", mobileNumberUpdateDto);
            iCardsService.rollbackMobileNumber(mobileNumberUpdateDto);
        };
    }
}
