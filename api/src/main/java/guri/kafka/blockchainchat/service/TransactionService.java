package guri.kafka.blockchainchat.service;

import guri.kafka.blockchainchat.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final OrderService orderService;

//    public BiConsumer<Long, Order>

}
