package vn.elca.training.microservice.common.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import vn.elca.training.microservice.common.converter.MessageConverter;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public abstract class AbstractMessageListener implements MessageListener {
    private final MessageConverter messageConverter;
    private final List<CommandExecutor> commandExecutors;

    @Override
    public void receive(String message) {
        var command = messageConverter.deserialize(message);
        if (CollectionUtils.isNotEmpty(commandExecutors)) {
            commandExecutors
                    .stream()
                    .filter(cmd -> cmd.accept(command.getClass()))
                    .findAny()
                    .ifPresentOrElse(cmd -> cmd.executeCommand(command), () -> new RuntimeException("Cannot find any supported executor"));
        } else {
            log.info("Service has no command executor");
        }
    }
}
