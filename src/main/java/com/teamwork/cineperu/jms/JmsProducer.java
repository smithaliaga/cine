package com.teamwork.cineperu.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsProducer {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${jms.cola.envio}")
    String destinationQueue;

    public void send(String msg) {
        jmsTemplate.convertAndSend(destinationQueue, (msg));
    }

}