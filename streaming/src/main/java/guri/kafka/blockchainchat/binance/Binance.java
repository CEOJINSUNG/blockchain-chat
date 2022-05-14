package guri.kafka.blockchainchat.binance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("binance")
public class Binance {
    @Id
    private Long id;
    private String coinPairName;

    private String eventType;
    private Long eventTime;
    private String symbol;
    private Long openTime;
    private String open;
    private String high;
    private String low;
    private String close;
    private String volume;
    private Long closeTime;
    private String intervalId;
    private Long firstTradeId;
    private Long lastTradeId;
    private String quoteAssetVolume;
    private Long numberOfTrades;
    private String takerBuyBaseAssetVolume;
    private String takerBuyQuoteAssetVolume;
    private Boolean isBarFinal;

}
