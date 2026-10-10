package com.HBank.cards.CQRS_EventSourcing.Query.handler;


import com.HBank.cards.CQRS_EventSourcing.Query.FindCardQuery;
import com.HBank.cards.dto.CardsDto;
import com.HBank.cards.service.ICardsService;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardQueryHandler {

    private final ICardsService iCardsService;

    @QueryHandler
    public CardsDto findCard(FindCardQuery query) {
        CardsDto card = iCardsService.fetchCard(query.getMobileNumber());
        return card;
    }

}
