package guri.kafka.blockchainchat.repository;

import guri.kafka.blockchainchat.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
