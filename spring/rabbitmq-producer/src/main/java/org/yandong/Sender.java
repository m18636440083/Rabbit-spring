package org.yandong;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * spring整合RabbitMQ
 * 消息生产者
 */
public class Sender {
    public static void main(String[] args) {
        // 创建spring容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-rabbitmq-producer.xml");
        // 从容器中获取对象
        RabbitTemplate template = context.getBean(RabbitTemplate.class);
        // 发送消息
        Map<String, String> map = new HashMap<>();
        map.put("name", "美猴王");
        map.put("iphone", "123456789");
        template.convertAndSend("x","message.user", map);
        // template.convertAndSend("message.user", map);
        System.out.println("消息已发送成功");
        context.close();

    }
}
