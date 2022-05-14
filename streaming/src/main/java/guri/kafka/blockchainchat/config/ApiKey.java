package guri.kafka.blockchainchat.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ApiKey {
    @Value("${api-key}")
    private String apikey;
    @Value("${secret-key}")
    private String secretkey;

    private static ApiKey instance = new ApiKey();

    private ApiKey() {

    }

    public static ApiKey getInstance() {
        if (instance == null) {
            instance = new ApiKey();
        }
        return instance;
    }
}
