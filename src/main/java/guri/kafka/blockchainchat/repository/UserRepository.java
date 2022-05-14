package guri.kafka.blockchainchat.repository;

import guri.kafka.blockchainchat.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
