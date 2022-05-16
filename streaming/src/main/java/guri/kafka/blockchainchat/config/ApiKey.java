package guri.kafka.blockchainchat.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ApiKey {
    @Value("${external.api-key}")
    private String apikey;
    @Value("${external.secret-key}")
    private String secretkey;

    public static ApiKey instance = new ApiKey();

    public ApiKey() {

    }

    public static ApiKey getInstance() {
        if (instance == null) {
            instance = new ApiKey();
        }
        return instance;
    }
}
