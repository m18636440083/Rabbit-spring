package org.yandong;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * spring整合RabbitMQ
 * 消息生产者
 * 设置消息TTL的生产者
 *
 * 如果同时设置了queue和message的TTL值，则二者中较小的才会起作用
 */
public class Sender2 {
    public static void main(String[] args) {
        // 创建spring容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-rabbitmq-producer.xml");
        // 从容器中获取对象
        RabbitTemplate template = context.getBean(RabbitTemplate.class);
        // 创建消息配置对象
        MessageProperties messageProperties = new MessageProperties();
        // 设置消息过期时间
        messageProperties.setExpiration("6000");
        // 创建消息
        Message message = new Message("设置单条消息六秒后自动删除".getBytes(), messageProperties);
        // 发送消息
        template.convertAndSend("message.user", message);
        System.out.println("消息已经发送");
        context.close();

    }
}
