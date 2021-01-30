package dev.luke10x.h2sproxy.jms;

import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

import javax.jms.Session;

@Configuration
@EnableJms
public class JmsConfig {

    SQSConnectionFactory connectionFactory =
            SQSConnectionFactory.builder()
                    .withRegion(Region.getRegion(Regions.US_EAST_1))
                    .withEndpoint("http://sqs:9324")
                    .withAWSCredentialsProvider(new EnvironmentVariableCredentialsProvider())
                    .build();

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(this.connectionFactory);
        factory.setDestinationResolver(new DynamicDestinationResolver());
        factory.setConcurrency("3-10");
        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        return factory;
    }

    @Bean
    public JmsTemplate defaultJmsTemplate() {
        return new JmsTemplate(this.connectionFactory);
    }
}