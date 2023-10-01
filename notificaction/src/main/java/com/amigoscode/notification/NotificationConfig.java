package com.amigoscode.notification;

import lombok.Getter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class NotificationConfig {

    @Value("${rabbitmq.exchanges.internal}")
    private String exchange;

    @Value("${rabbitmq.queues.notification}")
    private String queue;

    @Value("${rabbitmq.routing-keys.internal-notification}")
    private String route;

    @Bean
    public TopicExchange internalTopicExchange() {
        return new TopicExchange(this.exchange);
    }
    @Bean
    public Queue notificationQueue() {
        return new Queue(this.queue);
    }
    @Bean
    public Binding internalToNotification() {
        return BindingBuilder
                .bind(notificationQueue())
                .to(internalTopicExchange())
                .with(this.route);
    }
}
