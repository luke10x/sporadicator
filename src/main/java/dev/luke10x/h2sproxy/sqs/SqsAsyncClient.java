package dev.luke10x.h2sproxy.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import dev.luke10x.h2sproxy.domain.response.FutureResponse;

import dev.luke10x.h2sproxy.domain.AsyncClient;
import dev.luke10x.h2sproxy.domain.request.Request;
import dev.luke10x.h2sproxy.domain.response.Response;

import dev.luke10x.h2sproxy.jms.JmsFutureResponse;
import dev.luke10x.h2sproxy.jms.ThumbnailResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

@Component("sqsAsyncClient")
public class SqsAsyncClient implements AsyncClient {

    @Autowired
    protected JmsTemplate defaultJmsTemplate;

    private static final String COMMAND_QUEUE = "bus-commands";
    private static final String EVENT_QUEUE_URL = "http://sqs:9324/queue/bus-events";

    @Override
    public FutureResponse executeAsync(Request request) {
        String body = new String(request.getBody(), StandardCharsets.UTF_8);

        final AtomicReference<Message> sentMessageNet = new AtomicReference<>();
        defaultJmsTemplate.convertAndSend(
                COMMAND_QUEUE,
                body,
                m -> {
                    sentMessageNet.set(m);
                    return m;
                }
        );
        try {
            // That is the easiest way to find the messageId of newly sent message using JMS
            // also this ID comes in format "ID:{some-uuid}" so it needs to be trimmed...
            String messageId = sentMessageNet.get().getJMSMessageID().split(":")[1];

            return new JmsFutureResponse(messageId, body);
        } catch (JMSException e) {
            throw new RuntimeException("Cannot get messageID after the message is sent");
        }
    }
}