package vn.elca.training.microservice.common.kafka;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.Lifecycle;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.MethodKafkaListenerEndpoint;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.stereotype.Component;
import vn.elca.training.microservice.common.messaging.MessageClient;
import vn.elca.training.microservice.common.messaging.MessageListener;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaMessageClient implements MessageClient {
    private static final String GROUP_ID = "demo";
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaListenerContainerFactory<?> factory;
    private final Map<String, MessageListenerContainer> listenerContainerMap = new ConcurrentHashMap<>();

    @Override
    public void start() {
        log.info("Starting Message Client");
    }

    @Override
    public void stop() {
        listenerContainerMap.values().forEach(Lifecycle::stop);
        log.info("Stopped Message Client");
    }

    @Override
    public void sendMessage(String topic, String message) {
        log.info("Send message to {}", topic);
        kafkaTemplate.send(topic, message);
    }

    @Override
    public void sendMessage(String topic, String key, String message) {
        log.info("Send message to {}", topic);
        kafkaTemplate.send(topic, key, message);
    }

    @Override
    @SneakyThrows
    public void subscribeToTopic(String topic, MessageListener listener) {
        var kafkaMsgListener = new KafkaMessageListener(listener);
        var listenerEndpoint = new MethodKafkaListenerEndpoint<String, String>();
        listenerEndpoint.setMessageHandlerMethodFactory(new DefaultMessageHandlerMethodFactory());
        listenerEndpoint.setBean(kafkaMsgListener);
        listenerEndpoint.setMethod(kafkaMsgListener.getClass().getMethod("onMessage", ConsumerRecord.class));
        listenerEndpoint.setTopics(topic);
        listenerEndpoint.setGroupId(GROUP_ID);
        listenerEndpoint.setGroup(GROUP_ID);
        listenerEndpoint.setId(topic + "_" + LocalDateTime.now().toString());
        var listenerContainer = factory.createListenerContainer(listenerEndpoint);
        listenerContainer.getContainerProperties().setMessageListener(kafkaMsgListener);
        listenerContainer.start();
        listenerContainerMap.put(topic, listenerContainer);
        log.info("Subscribed to {} with id {}", topic, listenerContainer.getListenerId());
    }

    @Override
    public void unsubscribeToTopic(String topic) {
        if (listenerContainerMap.containsKey(topic)) {
            listenerContainerMap.get(topic).stop(() -> log.info("Unsubscribed to {}", topic));
        }
    }
}
