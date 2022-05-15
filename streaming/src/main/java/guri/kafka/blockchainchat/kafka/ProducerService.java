package guri.kafka.blockchainchat.kafka;

import guri.kafka.blockchainchat.binance.Binance;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProducerService {

    // 첫번째 요소는 카프카 테스트용이고 두번째 요소는 바이낸스 데이터를 전송할 카프카 테스트용이다.
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, Binance> binanceKafkaTemplate;

    public void sendMessage(String message) {
        System.out.println("producer message : " + message);
        // 여기서 주제는 consumer 에서 설정한 test를 넣어주고 그 다음 파라미터에는 실제로 보낼 메시지를 넣으면 된다.
        this.kafkaTemplate.send("test", message);
    }

    // 바이낸스 웹소켓으로 들어온 데이터를 여기로 전달함
    public void sendBinanceData(Binance binance) {
        this.binanceKafkaTemplate.send("binance", binance);
    }
}
