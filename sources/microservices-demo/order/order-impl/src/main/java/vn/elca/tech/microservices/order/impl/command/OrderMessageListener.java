package vn.elca.tech.microservices.order.impl.command;

import org.springframework.stereotype.Component;
import vn.elca.training.microservice.common.converter.MessageConverter;
import vn.elca.training.microservice.common.messaging.AbstractMessageListener;
import vn.elca.training.microservice.common.messaging.CommandExecutor;

import java.util.List;

@Component
public class OrderMessageListener extends AbstractMessageListener {
    public OrderMessageListener(MessageConverter messageConverter, List<CommandExecutor> commandExecutors) {
        super(messageConverter, commandExecutors);
    }
}
