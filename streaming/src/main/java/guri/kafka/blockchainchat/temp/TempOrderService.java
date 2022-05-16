package guri.kafka.blockchainchat.temp;

import guri.kafka.blockchainchat.domain.OrderType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

@SpringBootApplication
public class TempOrderService {
    private static long tempOrderId = 0;
    private static final Random r = new Random();

    public static void main(String[] args) {
        SpringApplication.run(TempOrderService.class, args);
    }

    LinkedList<TempOrder> buyOrder = new LinkedList<>();

    LinkedList<TempOrder> sellOrder = new LinkedList<>();

    @Bean
    public Supplier<Message<TempOrder>> orderBuySupplier() {
        buyOrder.add(new TempOrder(++tempOrderId, 1, 1, 100, LocalDateTime.now(), OrderType.BUY, 1000));
        buyOrder.add(new TempOrder(++tempOrderId, 2, 1, 100, LocalDateTime.now(), OrderType.BUY, 400));
        buyOrder.add(new TempOrder(++tempOrderId, 3, 1, 100, LocalDateTime.now(), OrderType.BUY, 200));
        buyOrder.add(new TempOrder(++tempOrderId, 4, 1, 100, LocalDateTime.now(), OrderType.BUY, 1400));
        buyOrder.add(new TempOrder(++tempOrderId, 5, 1, 100, LocalDateTime.now(), OrderType.BUY, 1300));
        buyOrder.add(new TempOrder(++tempOrderId, 6, 1, 100, LocalDateTime.now(), OrderType.BUY, 1060));
        return () -> {
          if (buyOrder.peek() != null) {
              Message<TempOrder> message = MessageBuilder
                  .withPayload(buyOrder.peek())
                  .setHeader(KafkaHeaders.MESSAGE_KEY, Objects.requireNonNull(buyOrder.poll()).getId())
                  .build();
              System.out.println("Buy " + message.getPayload().getAmount());
              return message;
          } else {
              return null;
          }
        };
    }

    @Bean
    public Supplier<Message<TempOrder>> orderSellSupplier() {
        sellOrder.add(new TempOrder(++tempOrderId, 7, 1, 100, LocalDateTime.now(), OrderType.SELL, 700));
        sellOrder.add(new TempOrder(++tempOrderId, 8, 1, 100, LocalDateTime.now(), OrderType.SELL, 900));
        sellOrder.add(new TempOrder(++tempOrderId, 9, 1, 100, LocalDateTime.now(), OrderType.SELL, 100));
        sellOrder.add(new TempOrder(++tempOrderId, 10, 1, 100, LocalDateTime.now(), OrderType.SELL, 1300));
        sellOrder.add(new TempOrder(++tempOrderId, 11, 1, 100, LocalDateTime.now(), OrderType.SELL, 1300));
        sellOrder.add(new TempOrder(++tempOrderId, 12, 1, 100, LocalDateTime.now(), OrderType.SELL, 1460));
        return () -> {
            if (sellOrder.peek() != null) {
                Message<TempOrder> message = MessageBuilder
                    .withPayload(sellOrder.peek())
                    .setHeader(KafkaHeaders.MESSAGE_KEY, Objects.requireNonNull(sellOrder.poll()).getId())
                    .build();
                System.out.println("Sell " + message.getPayload().getAmount());
                return message;
            } else {
                return null;
            }
        };
    }
}
