package vn.elca.training.microservices.account.impl.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.elca.training.microservice.common.converter.MessageConverter;
import vn.elca.training.microservices.account.dto.GetUserRequest;
import vn.elca.training.microservices.account.impl.model.UserEntity;
import vn.elca.training.microservices.account.impl.repository.UserRepository;

@Service
@Transactional(rollbackFor = Throwable.class)
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final MessageConverter messageConverter;

    public UserEntity getUserByNumber(String number) {
        return userRepository.findByNumber(number);
    }

    @PostConstruct
    public void initCommand() {
        var command = new GetUserRequest();
        command.setNumber("00001");
        var msg = messageConverter.serialize(command);
        log.info(msg);
    }
}
