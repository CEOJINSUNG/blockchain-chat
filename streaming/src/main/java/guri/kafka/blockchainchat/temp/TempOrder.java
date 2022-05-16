package guri.kafka.blockchainchat.temp;

import guri.kafka.blockchainchat.domain.OrderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TempOrder {
    private Long id;
    private int user_id;
    private int product_id;
    private int productCount;
    private LocalDateTime createdDate;
    private OrderType orderType;
    private int amount;
}
