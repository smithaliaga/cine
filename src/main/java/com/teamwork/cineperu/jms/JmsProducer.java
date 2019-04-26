package com.teamwork.cineperu.jms;

import com.teamwork.cineperu.entidad.Entrada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class JmsProducer {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${jms.cola.envio}")
    String destinationQueue;

    @Value("${jms.cola.respuesta}")
    String responseQueue;

    public void send(String msg) {
        jmsTemplate.convertAndSend(destinationQueue, (msg));
    }

    public void enviarRecibir(String msg){
        String id = UUID.randomUUID().toString();
        jmsTemplate.convertAndSend(destinationQueue, msg, m ->{
            m.setJMSCorrelationID(id);
            return m;
        });

        System.out.println("CINEFILOS - ENVIANDO MENSAJE A COLA: " + id);

        String responseMessage = (String) jmsTemplate.receiveSelectedAndConvert(responseQueue,
                "JMSCorrelationID='" + id + "'");

        System.out.println("CINEFILOS - RECIBE MENSAJE DEL SERVIDOR: " + responseMessage);
    }

}