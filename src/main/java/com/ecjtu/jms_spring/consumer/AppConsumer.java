package com.ecjtu.jms_spring.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppConsumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:consumer.xml");

        // 这里关闭的时候可能会出现问题 因为消费的过程是异步的
    }
}
