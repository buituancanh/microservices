package vn.elca.training.microservices.account.impl.command;

import org.springframework.stereotype.Component;
import vn.elca.training.microservice.common.converter.MessageConverter;
import vn.elca.training.microservice.common.messaging.AbstractMessageListener;
import vn.elca.training.microservice.common.messaging.CommandExecutor;

import java.util.List;

@Component
public class AccountMessageListener extends AbstractMessageListener {
    public AccountMessageListener(MessageConverter messageConverter, List<CommandExecutor> commandExecutors) {
        super(messageConverter, commandExecutors);
    }
}
