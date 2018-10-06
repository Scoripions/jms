package com.ecjtu.jms_spring.producer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 这里采用的是队列的方式
 * 如果使用主题订阅的方式 直接在实现类中修改对应的
 */
public class AppProducer {
    public static void main(String[] args) {
        // 装载IOC
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:producer.xml");
        ProducerService service = context.getBean(ProducerService.class);
        for (int i = 0; i < 100; i++) {
            service.sendMessage("test.."+i);
        }

        // 关闭连接 如果使用的是ApplicationContext接收的话 将不会进行关闭
        context.close();
    }
}
