package org.yandong.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class TestRunner {
    public static void main(String[] args) throws IOException {
        // 获得容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-rabbitmq-consumer.xml");
        // 让程序一直运行，别终止
        System.in.read();
    }
}
