package guri.kafka.blockchainchat.transaction

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
class Transaction(
    @Id
    var id: Long? = null,
    var buyOrderId: Long,
    var sellOrderId: Long,
    var amount: Int,
    var price: Int
) {
}