package guri.kafka.blockchainchat.transaction

import guri.kafka.blockchainchat.product.Product

data class TransactionTotalWithProduct(
    @Transient
    var transaction: Transaction,

    @Transient
    var product: Product
) {
}