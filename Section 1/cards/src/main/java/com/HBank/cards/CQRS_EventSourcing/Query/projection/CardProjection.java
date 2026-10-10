package com.HBank.cards.CQRS_EventSourcing.Query.projection;

import com.HBank.cards.CQRS_EventSourcing.Command.event.CardCreatedEvent;
import com.HBank.cards.CQRS_EventSourcing.Command.event.CardDeletedEvent;
import com.HBank.cards.CQRS_EventSourcing.Command.event.CardUpdatedEvent;
import com.HBank.cards.entity.Cards;
import com.HBank.cards.service.ICardsService;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ProcessingGroup("card-group")
public class CardProjection {

    private final ICardsService iCardsService;

    @EventHandler
    public void on(CardCreatedEvent event) {
        Cards cardEntity = new Cards();
        BeanUtils.copyProperties(event, cardEntity);
        iCardsService.createCard(cardEntity);
    }

    @EventHandler
    public void on(CardUpdatedEvent event) {
        iCardsService.updateCard(event);
    }

    @EventHandler
    public void on(CardDeletedEvent event) {
        iCardsService.deleteCard(event.getCardNumber());
    }

}
