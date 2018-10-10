package it.tim.pay.model.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "integration")
@Data
@Component
public class IntegrationConfiguration {
    private NetsConfiguration nets;
    
    @Data
    public static class NetsConfiguration {
        private String find;
    }

}
