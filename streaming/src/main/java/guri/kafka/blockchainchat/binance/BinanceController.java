package guri.kafka.blockchainchat.binance;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/coin") // websocket 에서 매핑하고자 하는 기본 url 서버
public class BinanceController {

    private final KafkaTemplate<String, Binance> kafkaTemplate;
    private final BinanceService binanceService;

    // 여기서 기업이 코인을 발행을 하고 코인 데이터를 입력을 하면 여기서 데이터가 넘겨짐
    @PostMapping(value = "/publish")
    public void sendBinanceData(@RequestBody Binance binance){
        try {
            this.kafkaTemplate.send("binance", binance).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 프론트에서 /coin/binance/{coinPairName} 을 get 요청하면 종류에 따른 코인 데이터를 받게 됨
    // Websocket 에서 프론트에서 /topic/binance 를 가지고 있으면 여기로 메시지를 전달함
    @MessageMapping("/binance/{coinPairName}")
    @SendTo("/topic/binance")
    public Binance broadcastGroupMessage(@Payload Binance binance, @PathVariable String coinPairName) {
        binanceService.askCoinData(coinPairName);
        return binance;
    }
}
