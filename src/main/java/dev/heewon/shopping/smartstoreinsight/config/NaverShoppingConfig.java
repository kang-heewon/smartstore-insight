package dev.heewon.shopping.smartstoreinsight.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConstructorBinding
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "smartstore.data.naver-shopping")
public class NaverShoppingConfig {
    private String url;
}
