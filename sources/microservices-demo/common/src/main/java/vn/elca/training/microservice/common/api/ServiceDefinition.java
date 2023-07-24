package vn.elca.training.microservice.common.api;

public interface ServiceDefinition {
    String serviceName();

    String getInputQueue();

    String getOutputTopic();

    default void start() {}
}
