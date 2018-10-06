package com.ecjtu.p2p;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消费端
 */
public class AppConsumer {

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
            // 创建消费者
            MessageConsumer consumer = session.createConsumer(destination);
            // 消费消息
            consumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    TextMessage message1 = (TextMessage) message;
                    System.out.println("收到的消息为:" + message1);
                }
            });
            // 关闭会话 这里要注意因为是异步的操作 所以得在监听收完消息之后才能进行关闭
            // connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
