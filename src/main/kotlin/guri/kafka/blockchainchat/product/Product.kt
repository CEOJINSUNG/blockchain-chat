package guri.kafka.blockchainchat.product

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
class Product(
    @Id
    var id: Long? = null,
    var name: String,
    var price: Int
)