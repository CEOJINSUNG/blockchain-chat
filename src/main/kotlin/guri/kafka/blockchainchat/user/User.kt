package guri.kafka.blockchainchat.user

import lombok.Getter
import lombok.Setter
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Getter
@Setter
@Table
class User(
    @Id
    var user_id: Long,
    var email: String,
    var nickname: String,
) {
}