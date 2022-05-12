package guri.kafka.blockchainchat.user

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
class User(
    @Id
    var user_id: Long,
    var email: String,
    var nickname: String,
) {
}