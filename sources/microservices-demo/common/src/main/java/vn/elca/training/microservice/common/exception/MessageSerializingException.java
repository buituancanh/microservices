package vn.elca.training.microservice.common.exception;

import jakarta.xml.bind.JAXBException;

import java.io.IOException;

public class MessageSerializingException extends RuntimeException {
    public MessageSerializingException(JAXBException e, String exMessage) {
        super(exMessage, e);
    }

    public MessageSerializingException(IOException e, String exMessage) {
        super(exMessage, e);
    }
}
