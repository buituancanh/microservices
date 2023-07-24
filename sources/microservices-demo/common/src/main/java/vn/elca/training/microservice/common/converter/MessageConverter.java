package vn.elca.training.microservice.common.converter;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.elca.training.microservice.common.api.ApiConfiguration;
import vn.elca.training.microservice.common.exception.MessageDeserializingException;
import vn.elca.training.microservice.common.exception.MessageSerializingException;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class MessageConverter {
    private final JAXBContext jaxbContext;
    public MessageConverter(List<ApiConfiguration> apiConfigurations) throws JAXBException {
        var supportedPackages = apiConfigurations.stream()
                .map(ApiConfiguration::supportedPackages)
                .flatMap(Collection::stream)
                .sorted()
                .collect(Collectors.joining(":"));
        this.jaxbContext = JAXBContext.newInstance(supportedPackages);
    }

    public <T> T deserialize(String message) {
        //noinspection unchecked - unchecked cast is required to make it work
        return deserialize(message, (Class<T>)  Void.class);
    }

    public <T> T deserialize(String message, Class<T> clazz) {
        try (var stringReader = new StringReader(message)) {
            var unmarshaller = jaxbContext.createUnmarshaller();
            var obj = unmarshaller.unmarshal(stringReader);
            //noinspection unchecked - unchecked cast is required to make it work
            var effectiveCommandType = clazz != Void.class ? clazz : (Class<T>) obj.getClass();
            return effectiveCommandType.cast(obj);
        } catch (JAXBException e) {
            var exMessage = Optional.ofNullable(e.getLinkedException()).map(Throwable::getMessage)
                    .orElse(e.getMessage());
            throw new MessageDeserializingException(e, exMessage);
        }
    }

    public String serialize(Object message) {
        try (var stringWriter = new StringWriter()) {
            var marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(message, stringWriter);
            return stringWriter.toString();
        } catch (JAXBException e) {
            var exMessage = Optional.ofNullable(e.getLinkedException()).map(Throwable::getMessage)
                    .orElse(e.getMessage());
            throw new MessageSerializingException(e, exMessage);
        } catch (IOException e) {
            throw new MessageSerializingException(e, e.getMessage());
        }
    }
}
