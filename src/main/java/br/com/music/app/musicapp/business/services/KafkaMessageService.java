package br.com.music.app.musicapp.business.services;

import br.com.music.app.musicapp.business.dto.responses.MessageResponse;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageService {

    @KafkaListener(id = "messages",
            containerFactory = "messageListener" ,
            topicPartitions = {
            @TopicPartition(topic = "quickstart-events", partitionOffsets = {
                    @PartitionOffset(partition = "0", initialOffset = "0", seekPosition = "BEGINNING")
            })
            })
    public void consumeMessage(MessageResponse message){
        System.out.println("Messages: "+message.getMessage());
    }
}
