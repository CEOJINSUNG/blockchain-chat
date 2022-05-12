package guri.kafka.blockchainchat.order

class OrderSellBuy(
    var sellAmount: Int,
    var buyAmount: Int
) {

    fun addSell(sellAmount : Int) : OrderSellBuy {
        this.sellAmount += sellAmount
        return this
    }

    fun addBuy(buyAmount: Int) : OrderSellBuy {
        this.buyAmount += buyAmount
        return this
    }
}