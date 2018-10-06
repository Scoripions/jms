package com.ecjtu.jms_spring.producer;

/**
 * 生产者服务接口
 */
public interface ProducerService {
    void sendMessage(String message);
}
