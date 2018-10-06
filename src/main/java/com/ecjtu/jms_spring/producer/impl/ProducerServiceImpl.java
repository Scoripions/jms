package com.ecjtu.jms_spring.producer.impl;

import com.ecjtu.jms_spring.producer.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.annotation.Resource;
import javax.jms.*;

public class ProducerServiceImpl implements ProducerService {
    @Autowired
    JmsTemplate jmsTemplate;
    /**
     * 这里可以进行切换修改对应的模式
     */
    @Resource(name = "topicDestination")
    Destination destination;

    public void sendMessage(final String message) {
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(message);
                System.out.println("发送的消息为:" + textMessage.getText());
                return textMessage;
            }
        });
    }
}
