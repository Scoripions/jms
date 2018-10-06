package com.ecjtu.p2p;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 生产端
 */
public class AppProducer {

    private static  final String QUEUE = "test-queue";
    private static final String URL = "tcp://127.0.0.1:61616";
    public static void main(String[] args) {

        try {
            // 创建连接工厂
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
            // 创建连接对象
            Connection connection = connectionFactory.createConnection();
            // 启动连接
            connection.start();
            // 创建会话
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 创建目标
            Destination destination = session.createQueue(QUEUE);
            // 创建生产者
            MessageProducer producer = session.createProducer(destination);

            for (int i = 0; i < 100; i++) {
                // 创建消息
                TextMessage message = session.createTextMessage("test" + i);
                // 发布消息
                producer.send(message);
            }
            // 关闭会话
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
