package guri.kafka.blockchainchat.transaction

data class TransactionalTotal(
    var amount: Int,
    var productAmount : Int,
    var totalAmount : Long
) {
}