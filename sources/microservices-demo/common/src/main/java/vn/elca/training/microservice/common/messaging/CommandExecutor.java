package vn.elca.training.microservice.common.messaging;

public interface CommandExecutor<T> {

    void executeCommand(T commandMessage);

    boolean accept(Class<?> clazz);
}
