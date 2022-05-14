package guri.kafka.blockchainchat.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        System.out.println("producer message : " + message);
        // 여기서 주제는 consumer 에서 설정한 stock을 넣어주고 그 다음 파라미터에는 실제로 보낼 메시지를 넣으면 된다.
        this.kafkaTemplate.send("stock", message);
    }
}
