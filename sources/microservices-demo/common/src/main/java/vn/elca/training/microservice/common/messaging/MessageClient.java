package vn.elca.training.microservice.common.messaging;

public interface MessageClient {

    void start();

    void stop();

    void sendMessage(String topic, String message);

    void sendMessage(String topic, String key, String message);

    void subscribeToTopic(String topic, MessageListener listener);

    void unsubscribeToTopic(String topic);
}
