package com.HBank.customer.CQRS_EventSourcing.Command.Interceptor;


import com.HBank.customer.CQRS_EventSourcing.Command.CreateCustomerCommand;
import com.HBank.customer.CQRS_EventSourcing.Command.DeleteCustomerCommand;
import com.HBank.customer.CQRS_EventSourcing.Command.UpdateCustomerCommand;
import com.HBank.customer.constants.CustomerConstants;
import com.HBank.customer.entity.Customer;
import com.HBank.customer.exception.CustomerAlreadyExistsException;
import com.HBank.customer.exception.ResourceNotFoundException;
import com.HBank.customer.repository.CustomerRepository;
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
public class CustomerCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    private final CustomerRepository customerRepository;

    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@Nonnull List<? extends CommandMessage<?>> messages) {
        return (index, command) -> {
            if(CreateCustomerCommand.class.equals(command.getPayloadType())) {
                CreateCustomerCommand createCustomerCommand = (CreateCustomerCommand) command.getPayload();
                Optional<Customer> optionalCustomer = customerRepository.
                        findByMobileNumberAndActiveSw(createCustomerCommand.getMobileNumber(), true);
                if (optionalCustomer.isPresent()) {
                    throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber "
                            + createCustomerCommand.getMobileNumber());
                }
            } else if(UpdateCustomerCommand.class.equals(command.getPayloadType())) {
                UpdateCustomerCommand updateCustomerCommand = (UpdateCustomerCommand) command.getPayload();
                Customer customer = customerRepository.findByMobileNumberAndActiveSw
                        (updateCustomerCommand.getMobileNumber(), true).orElseThrow(() ->
                        new ResourceNotFoundException("Customer", "mobileNumber", updateCustomerCommand.getMobileNumber()));
            } else if(DeleteCustomerCommand.class.equals(command.getPayloadType())) {
                DeleteCustomerCommand deleteCustomerCommand = (DeleteCustomerCommand) command.getPayload();
                Customer customer = customerRepository.findByCustomerIdAndActiveSw(deleteCustomerCommand.getCustomerId(),
                        CustomerConstants.ACTIVE_SW).orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId",
                        deleteCustomerCommand.getCustomerId()));
            }
            return command;
        };
    }
}