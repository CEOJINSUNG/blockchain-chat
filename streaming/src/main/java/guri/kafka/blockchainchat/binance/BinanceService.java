package guri.kafka.blockchainchat.binance;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.market.CandlestickInterval;
import guri.kafka.blockchainchat.config.ApiKey;
import guri.kafka.blockchainchat.kafka.ProducerService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Data
@Service
public class BinanceService {
    private ApiKey key;
    private String apiKey;
    private String secretKey;
    private BinanceRepository binanceRepository;
    private ProducerService producerService;

    @Autowired
    public BinanceService(ApiKey key, BinanceRepository binanceRepository, ProducerService producerService) {
        this.key = key;
        this.apiKey = key.getApikey();
        this.secretKey = key.getSecretkey();
        this.binanceRepository = binanceRepository;
        this.producerService = producerService;
    }

    private String coinPairName = "btcusdt";

    public void subscribeBinanceData() {
        BinanceApiWebSocketClient binanceApiWebSocketClient = BinanceApiClientFactory.newInstance(apiKey, secretKey).newWebSocketClient();
        binanceApiWebSocketClient.onCandlestickEvent(coinPairName, CandlestickInterval.ONE_MINUTE, response -> {
           if (response.getBarFinal()) {
               Binance binance = new Binance();
               binance.setCoinPairName(coinPairName);
               binance.setEventType(response.getEventType());
               binance.setEventTime(response.getEventTime());
               binance.setSymbol(response.getSymbol());
               binance.setOpenTime(response.getOpenTime());
               binance.setOpen(response.getOpen());
               binance.setCloseTime(response.getCloseTime());
               binance.setClose(response.getClose());
               binance.setHigh(response.getHigh());
               binance.setLow(response.getLow());
               binance.setVolume(response.getVolume());
               binance.setIntervalId(response.getIntervalId());
               binance.setFirstTradeId(response.getFirstTradeId());
               binance.setLastTradeId(response.getLastTradeId());
               binance.setQuoteAssetVolume(response.getQuoteAssetVolume());
               binance.setNumberOfTrades(response.getNumberOfTrades());
               binance.setTakerBuyBaseAssetVolume(response.getTakerBuyBaseAssetVolume());
               binance.setTakerBuyQuoteAssetVolume(response.getTakerBuyQuoteAssetVolume());
               binance.setIsBarFinal(response.getBarFinal());
               System.out.println("Final Bar: " + response.getSymbol() + " " + response.getHigh() + " " + response.getLow());

               binanceRepository.save(binance);
               producerService.sendMessage(response.getHigh());
           } else {
               System.out.println("Current Bar: " + response.getSymbol() + " " + response.getHigh() + " " + response.getLow());
           }
        });
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.subscribeBinanceData();
    }

}
