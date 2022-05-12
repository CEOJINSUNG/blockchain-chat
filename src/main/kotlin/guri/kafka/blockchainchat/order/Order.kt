package guri.kafka.blockchainchat.order

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("order")
class Order (
    @Id
    var id: Long? = null,
    var userId: Long,
    var productId: Long,
    var currentAmount: Int,
    var realizedAmount: Int,
    var createdTime : LocalDateTime,
    var orderType: OrderType
)