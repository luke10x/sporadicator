package dev.luke10x.h2sproxy.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.luke10x.h2sproxy.domain.FutureResponseRepository;
import dev.luke10x.h2sproxy.domain.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;

@Service
public class RemoteEventHandler {

    @Autowired
    FutureResponseRepository repository;

    @JmsListener(destination = "bus-events")
    public void createThumbnail(String requestJSON) throws JMSException {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JmsRequest message = objectMapper.readValue(requestJSON, JmsRequest.class);

            String requestId = message.getOriginalRequestMessageId();
            JmsFutureResponse futureResponse = (JmsFutureResponse) repository.get(requestId);
            if (futureResponse == null) {
                System.out.println("Such request not found in the repository");
                return;
            }
            futureResponse.finish(new Response(message.getResponse()));
        } catch (JsonProcessingException e) {
            throw new JMSException("Message came in unexpected format");
        }
    }
}