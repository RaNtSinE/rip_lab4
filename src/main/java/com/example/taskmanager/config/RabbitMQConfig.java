package com.example.taskmanager.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;

import java.util.logging.Logger;

public class RabbitMQConfig {
    Logger logger = Logger.getLogger(RabbitMQConfig.class.getName());

    //настраиваем соединение с RabbitMQ
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory("localhost");
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue queueGet() {
        return new Queue("queueGet");
    }

    @Bean
    public Queue queueCreateTask() {
        return new Queue("queueCreateTask");
    }

    @Bean
    public Queue queueUpdateTask() {
        return new Queue("queueUpdateTask");
    }

    @Bean
    public Queue queueDeleteTask() {
        return new Queue("queueDeleteTask");
    }

    @Bean
    public Queue queueUpdateIsComplete() {
        return new Queue("queueUpdateIsComplete");
    }

    @Bean
    public SimpleMessageListenerContainer messageListenerGet() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames("queueGet");
        container.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                logger.info("received from queueGet : " + new String(message.getBody()));
            }
        });
        return container;
    }

    @Bean
    public SimpleMessageListenerContainer messageListenerCreateTask() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames("queueCreateTask");
        container.setMessageListener(new MessageListener() {
            //тут ловим сообщения из queue1
            public void onMessage(Message message) {
                logger.info("received from queueCreateTask : " + new String(message.getBody()));
            }
        });
        return container;
    }

    @Bean
    public SimpleMessageListenerContainer messageListenerUpdateTask() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames("queueUpdateTask");
        container.setMessageListener(new MessageListener() {
            //тут ловим сообщения из queue1
            public void onMessage(Message message) {
                logger.info("received from queueUpdateTask : " + new String(message.getBody()));
            }
        });
        return container;
    }

    @Bean
    public SimpleMessageListenerContainer messageListenerDeleteTask() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames("queueDeleteTask");
        container.setMessageListener(new MessageListener() {
            //тут ловим сообщения из queue1
            public void onMessage(Message message) {
                logger.info("received from queueDeleteTask : " + new String(message.getBody()));
            }
        });
        return container;
    }

    @Bean
    public SimpleMessageListenerContainer messageListenerUpdateIsComplete() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames("queueUpdateIsComplete");
        container.setMessageListener(new MessageListener() {
            //тут ловим сообщения из queue1
            public void onMessage(Message message) {
                logger.info("received from queueUpdateIsComplete : " + new String(message.getBody()));
            }
        });
        return container;
    }
}
