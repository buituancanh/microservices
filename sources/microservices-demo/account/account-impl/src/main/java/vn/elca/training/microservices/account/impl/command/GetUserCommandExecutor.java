package vn.elca.training.microservices.account.impl.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.elca.training.microservice.common.converter.MessageConverter;
import vn.elca.training.microservice.common.messaging.CommandExecutor;
import vn.elca.training.microservice.common.messaging.MessageClient;
import vn.elca.training.microservices.account.api.AccountApiConfiguration;
import vn.elca.training.microservices.account.dto.GetUserRequest;
import vn.elca.training.microservices.account.impl.mapper.UserMapper;
import vn.elca.training.microservices.account.impl.services.UserService;

@Component
@Slf4j
@RequiredArgsConstructor
public class GetUserCommandExecutor implements CommandExecutor<GetUserRequest> {
    private final UserService userService;
    private final MessageClient messageClient;
    private final UserMapper userMapper;
    private final AccountApiConfiguration accountApiConfiguration;
    private final MessageConverter converter;
    @Override
    public void executeCommand(GetUserRequest commandMessage) {
        var user = userService.getUserByNumber(commandMessage.getNumber());
        var userResponse = userMapper.toUserResponse(user);
        messageClient.sendMessage(accountApiConfiguration.getOutputTopic(), converter.serialize(userResponse));
    }

    @Override
    public boolean accept(Class<?> clazz) {
        return clazz.isAssignableFrom(GetUserRequest.class);
    }
}
