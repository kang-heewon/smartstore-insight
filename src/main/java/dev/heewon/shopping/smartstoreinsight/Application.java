package dev.heewon.shopping.smartstoreinsight;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PreDestroy;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        final SpringApplication application = new SpringApplication(Application.class);
        application.setWebApplicationType(WebApplicationType.SERVLET);

        final ConfigurableApplicationContext ctx = application.run(args);
        final String serverPort = ctx.getEnvironment().getProperty("server.port");
        final String profile = ctx.getEnvironment().getProperty("spring.profiles.active");

        // startup notification
        final String banner = "SmartStoreInsightApplication started (profile=" + profile + ", port=" + serverPort + ")";
        System.out.println(banner);

        LoggerFactory.getLogger("ServerBoot").info(banner);

    }

    @PreDestroy
    public void onExit() {
        LoggerFactory.getLogger("ServerBoot").info("SmartStoreInsightApplication stopped");
    }
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
