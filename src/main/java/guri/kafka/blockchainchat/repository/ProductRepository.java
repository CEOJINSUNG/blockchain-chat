package guri.kafka.blockchainchat.repository;

import guri.kafka.blockchainchat.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
