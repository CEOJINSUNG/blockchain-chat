package guri.kafka.blockchainchat.temp;

import guri.kafka.blockchainchat.domain.OrderType;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Supplier;

public class TempOrderService {
    private static long tempOrderId = 0;
    private static final Random r = new Random();

    LinkedList<TempOrder> buyOrder = new LinkedList<>(List.of(
        new TempOrder(++tempOrderId, 1, 1, 100, LocalDateTime.now(), OrderType.BUY, 1000),
        new TempOrder(++tempOrderId, 2, 1, 100, LocalDateTime.now(), OrderType.BUY, 400),
        new TempOrder(++tempOrderId, 3, 1, 100, LocalDateTime.now(), OrderType.BUY, 200),
        new TempOrder(++tempOrderId, 4, 1, 100, LocalDateTime.now(), OrderType.BUY, 1400),
        new TempOrder(++tempOrderId, 5, 1, 100, LocalDateTime.now(), OrderType.BUY, 1300),
        new TempOrder(++tempOrderId, 6, 1, 100, LocalDateTime.now(), OrderType.BUY, 1060)
    ));

    LinkedList<TempOrder> sellOrder = new LinkedList<>(List.of(
        new TempOrder(++tempOrderId, 7, 1, 100, LocalDateTime.now(), OrderType.SELL, 700),
        new TempOrder(++tempOrderId, 8, 1, 100, LocalDateTime.now(), OrderType.SELL, 900),
        new TempOrder(++tempOrderId, 9, 1, 100, LocalDateTime.now(), OrderType.SELL, 100),
        new TempOrder(++tempOrderId, 10, 1, 100, LocalDateTime.now(), OrderType.SELL, 1300),
        new TempOrder(++tempOrderId, 11, 1, 100, LocalDateTime.now(), OrderType.SELL, 1300),
        new TempOrder(++tempOrderId, 12, 1, 100, LocalDateTime.now(), OrderType.SELL, 1460)
    ));

    @Bean
    public Supplier<Message<TempOrder>> orderBuySupplier() {
        return () -> {
          if (buyOrder.peek() != null) {
              Message<TempOrder> message = MessageBuilder
                  .withPayload(buyOrder.peek())
                  .setHeader(KafkaHeaders.MESSAGE_KEY, Objects.requireNonNull(buyOrder.poll()).getId())
                  .build();
              System.out.println("Order " + message.getPayload());
              return message;
          } else {
              return null;
          }
        };
    }

    @Bean
    public Supplier<Message<TempOrder>> orderSellSupplier() {
        return () -> {
            if (sellOrder.peek() != null) {
                Message<TempOrder> message = MessageBuilder
                    .withPayload(sellOrder.peek())
                    .setHeader(KafkaHeaders.MESSAGE_KEY, Objects.requireNonNull(sellOrder.poll()).getId())
                    .build();
                System.out.println("Order " + message.getPayload());
                return message;
            } else {
                return null;
            }
        };
    }
}
