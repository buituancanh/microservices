package vn.elca.training.microservice.common.exception;

import jakarta.xml.bind.JAXBException;

public class MessageDeserializingException extends RuntimeException {
    public MessageDeserializingException(JAXBException e, String exMessage) {
        super(exMessage, e);
    }
}
