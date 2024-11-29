package br.com.music.app.musicapp.business.services;

import br.com.music.app.musicapp.business.dto.responses.MessageResponse;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageService {

    @KafkaListener(topics = "teste-events", groupId = "group_id")
    public void consumeMessage(@Payload MessageResponse message){
        System.out.println("Messages: "+message.getMessage());
    }
}
