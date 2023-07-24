package vn.elca.training.microservice.common.api;

import java.util.List;

public interface ApiConfiguration {
    String getServiceName();

    String getInputQueue();

    String getOutputTopic();

    List<String> supportedPackages();
}
