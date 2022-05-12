package guri.kafka.blockchainchat.product

import lombok.Getter
import lombok.Setter
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Getter
@Setter
@Table
class Product (name: String, price: Int) {

    @Id
    var id: Long? = null
    var name: String = name
    var price: Int = price

}