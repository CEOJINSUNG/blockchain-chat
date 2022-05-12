package guri.kafka.blockchainchat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BlockchainChatApplication

fun main(args: Array<String>) {
	runApplication<BlockchainChatApplication>(*args)
}
