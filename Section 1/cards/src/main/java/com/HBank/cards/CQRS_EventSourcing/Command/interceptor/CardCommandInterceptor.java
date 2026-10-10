package com.HBank.cards.CQRS_EventSourcing.Command.interceptor;


import com.HBank.cards.CQRS_EventSourcing.Command.CreateCardCommand;
import com.HBank.cards.CQRS_EventSourcing.Command.DeleteCardCommand;
import com.HBank.cards.CQRS_EventSourcing.Command.UpdateCardCommand;
import com.HBank.cards.constants.CardsConstants;
import com.HBank.cards.entity.Cards;
import com.HBank.cards.exception.CardAlreadyExistsException;
import com.HBank.cards.exception.ResourceNotFoundException;
import com.HBank.cards.repository.CardsRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class CardCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    private final CardsRepository cardsRepository;

    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@Nonnull List<? extends
            CommandMessage<?>> messages) {
        return (index, command) -> {
            if (CreateCardCommand.class.equals(command.getPayloadType())) {
                CreateCardCommand createCardCommand = (CreateCardCommand) command.getPayload();
                Optional<Cards> optionalCards = cardsRepository.findByMobileNumberAndActiveSw(
                        createCardCommand.getMobileNumber(), true);
                if (optionalCards.isPresent()) {
                    throw new CardAlreadyExistsException("Card already created with given mobileNumber "
                            + createCardCommand.getMobileNumber());
                }
            } else if (UpdateCardCommand.class.equals(command.getPayloadType())) {
                UpdateCardCommand updateCardCommand = (UpdateCardCommand) command.getPayload();
                Cards card = cardsRepository.findByMobileNumberAndActiveSw
                        (updateCardCommand.getMobileNumber(), true).orElseThrow(() ->
                        new ResourceNotFoundException("Card", "mobileNumber", updateCardCommand.getMobileNumber()));
            } else if (DeleteCardCommand.class.equals(command.getPayloadType())) {
                DeleteCardCommand deleteCardCommand = (DeleteCardCommand) command.getPayload();
                Cards card = cardsRepository.findByCardNumberAndActiveSw(deleteCardCommand.getCardNumber(),
                        CardsConstants.ACTIVE_SW).orElseThrow(() -> new ResourceNotFoundException("Card", "cardNumber",
                        deleteCardCommand.getCardNumber().toString()));
            }
            return command;
        };
    }
}
