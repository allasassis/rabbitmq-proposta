package com.allasassis.rabbitmqapp.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.pendingpropose.exchange}")
    private String pendingProposeExchange;

    @Value("${rabbitmq.finishedpropose.exchange}")
    private String finishedProposeExchange;

    // Spring automatically inject the connectionFactory
    @Bean
    public RabbitAdmin createRabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> initializeAdmin(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }

    // QUEUES

    @Bean
    public Queue createQueueMsCreditAnalysis() {
        return QueueBuilder.durable("pending-propose.ms-credit-analysis").build();
    }

    @Bean
    public Queue createQueueMsNotification() {
        return QueueBuilder.durable("pending-propose.ms-notification").build();
    }

    @Bean
    public Queue createQueueMsFinishedPropose() {
        return QueueBuilder.durable("concluded-propose.ms-propose").build();
    }

    @Bean
    public Queue createQueueMsFinishedNotification() {
        return QueueBuilder.durable("concluded-propose.ms-notification").build();
    }

    // FANOUTS

    @Bean
    public FanoutExchange createFanoutExchangePendingPropose() {
        return ExchangeBuilder.fanoutExchange(pendingProposeExchange).build();
    }

    @Bean
    public FanoutExchange createFanoutExchangeFinishedPropose() {
        return ExchangeBuilder.fanoutExchange(finishedProposeExchange).build();
    }

    // BINDINGS

    @Bean
    public Binding createBindingPendingProposeMSCreditAnalysis() {
        return BindingBuilder.bind(createQueueMsCreditAnalysis()).to(createFanoutExchangePendingPropose());
    }

    @Bean
    public Binding createBindingPendingProposeMSNotification() {
        return BindingBuilder.bind(createQueueMsNotification()).to(createFanoutExchangePendingPropose());
    }

    @Bean
    public Binding createBindingFinishedProposeMSProposalApp() {
        return BindingBuilder.bind(createQueueMsFinishedPropose()).to(createFanoutExchangeFinishedPropose());
    }

    @Bean
    public Binding createBindingFinishedProposeMSNotification() {
        return BindingBuilder.bind(createQueueMsFinishedNotification()).to(createFanoutExchangeFinishedPropose());
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
}
