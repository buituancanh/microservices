package vn.elca.tech.microservices.order.impl.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.elca.tech.microservices.order.api.OrderApiConfiguration;
import vn.elca.tech.microservices.order.impl.model.OrderEntity;
import vn.elca.tech.microservices.order.impl.repository.OrderRepository;
import vn.elca.training.microservice.common.converter.MessageConverter;
import vn.elca.training.microservice.common.messaging.MessageClient;
import vn.elca.training.microservices.account.api.AccountApiConfiguration;
import vn.elca.training.microservices.account.dto.GetUserRequest;
import vn.elca.training.microservices.account.dto.GetUserResponse;
import vn.elca.training.microservices.order.dto.CreateOrder;
import vn.elca.training.microservices.order.dto.OrderCreated;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final AccountApiConfiguration accountApiConfiguration;
    private final MessageClient messageClient;
    private final MessageConverter messageConverter;
    private final OrderApiConfiguration orderApiConfiguration;
    @Value("${mail.input}")
    private final String mailInputTopic;

    public void createOrder(CreateOrder order) {
        messageClient.subscribeToTopic(accountApiConfiguration.getOutputTopic(), msg -> {
            // 1. Process response from account service
            var getUserResponse = messageConverter.deserialize(msg, GetUserResponse.class);
            // 2. Create order and save to DB
            var orderEntity = new OrderEntity();
            orderEntity.setTimestamp(LocalDateTime.now());
            orderEntity.setUserNumber(getUserResponse.getNumber());
            orderEntity.setAmount(order.getAmount());
            orderEntity.setEmail(getUserResponse.getBasicInfo().getEmail());
            var savedOrder = orderRepository.saveAndFlush(orderEntity);
            // 3. Send email to user
            messageClient.sendMessage(mailInputTopic, getUserResponse.getBasicInfo().getEmail(),
                    buildEmail(getUserResponse.getBasicInfo().getName(), orderEntity.getId(), orderEntity.getAmount()));
            // 4. Emit response event to output topic
            var response = new OrderCreated();
            response.setOrderNumber(savedOrder.getId());
            response.setTimestamp(response.getTimestamp());
            messageClient.sendMessage(orderApiConfiguration.getOutputTopic(), messageConverter.serialize(response));
        });

        var requestUser = new GetUserRequest();
        requestUser.setNumber(order.getAccountNumber());
        messageClient.sendMessage(accountApiConfiguration.getInputQueue(), messageConverter.serialize(requestUser));
    }

    private String buildEmail(String name, long id,  long amount) {
        var template = """
                Hello %s,
                
                Your order was created:
                - Number: %s
                - Amount: %s
                
                Thanks
                Moderator
                """;
        return String.format(template, name, id, amount);
    }
}
