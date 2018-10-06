package com.ecjtu.jms_spring.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ConsumerMessageListener implements MessageListener {
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println("接收的消息为:" + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}