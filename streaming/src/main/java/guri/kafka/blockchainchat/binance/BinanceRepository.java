package guri.kafka.blockchainchat.binance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BinanceRepository extends CrudRepository<Binance, Long> {
}
