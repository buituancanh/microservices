package vn.elca.training.microservice.common.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

@Slf4j
@RequiredArgsConstructor
public class KafkaMessageListener implements MessageListener<String, String> {
    private final vn.elca.training.microservice.common.messaging.MessageListener messageListener;
    @Override
    public void onMessage(ConsumerRecord<String, String> data) {
        log.info("Receiving new message: {}", data.value());
        messageListener.receive(data.value());
    }
}
