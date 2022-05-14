package guri.kafka.blockchainchat.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class KafkaProducerController {

    private final ProducerService producerService;

    @PostMapping(value = "/sendMessage")
    public void sendMessage(String message) {
        producerService.sendMessage(message);
    }
}
