package guri.kafka.blockchainchat.service;

import guri.kafka.blockchainchat.domain.Order;
import guri.kafka.blockchainchat.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order add(Order order) {
        return orderRepository.save(order);
    }

    public boolean checkAmount(Long buyOrderId, Long sellOrderId, int amount) {
        Order buyOrder = orderRepository.findById(buyOrderId).orElseThrow();
        Order sellOrder = orderRepository.findById(sellOrderId).orElseThrow();

        int buyAvailableAmount = buyOrder.getCurrentAmount() - buyOrder.getRealizedAmount();
        int sellAvailableAmount = sellOrder.getCurrentAmount() - sellOrder.getRealizedAmount();

        if (buyAvailableAmount >= amount && sellAvailableAmount >= amount) {
            buyOrder.setRealizedAmount(buyOrder.getRealizedAmount() + amount);
            sellOrder.setRealizedAmount(sellOrder.getRealizedAmount() + amount);
            orderRepository.save(buyOrder);
            orderRepository.save(sellOrder);
            return true;
        } else {
            return false;
        }
    }
}
