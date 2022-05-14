package guri.kafka.blockchainchat.repository;

import guri.kafka.blockchainchat.domain.Order;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Order> findById(Long id);

}
