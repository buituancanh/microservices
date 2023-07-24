package vn.elca.training.microservice.common.messaging;

public interface MessageListener {
    void receive(String message);
}
