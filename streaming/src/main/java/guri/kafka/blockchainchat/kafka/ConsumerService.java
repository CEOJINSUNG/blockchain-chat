package guri.kafka.blockchainchat.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConsumerService {
    @KafkaListener(topics = "stock", groupId = "blockchain-chat-group")
    public void consume(String message) throws IOException {
        System.out.println("consumer message : " + message);
    }
}
