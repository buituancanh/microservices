package vn.elca.tech.microservices.order.impl.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.elca.tech.microservices.order.impl.services.OrderService;
import vn.elca.training.microservice.common.messaging.CommandExecutor;
import vn.elca.training.microservices.order.dto.CreateOrder;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateOrderCommandExecutor implements CommandExecutor<CreateOrder> {
    private final OrderService orderService;
    @Override
    public void executeCommand(CreateOrder commandMessage) {
        orderService.createOrder(commandMessage);
    }

    @Override
    public boolean accept(Class<?> clazz) {
        return clazz.isAssignableFrom(CreateOrder.class);
    }
}
