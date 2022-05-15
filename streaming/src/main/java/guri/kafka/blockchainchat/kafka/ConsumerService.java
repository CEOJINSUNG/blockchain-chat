package guri.kafka.blockchainchat.kafka;

import guri.kafka.blockchainchat.binance.Binance;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class ConsumerService {

    private final SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topics = "test", groupId = "test-group")
    public void consume(String message) throws IOException {
        System.out.println("consumer message : " + message);
    }

    // binance 토픽으로 들어온 요소를 여기서 들음
    @KafkaListener(topics = "binance", groupId = "blockchain-chat-group")
    public void binanceConsume(Binance binance) {
        // 그러면 받은 데이터를 websocket 의 /topic/binance 로 Broadcast 상태로 보냄
        messagingTemplate.convertAndSend("/topic/binance", binance);
    }
}
