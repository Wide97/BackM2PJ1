package marcowidesott.BackM2PJ1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public String applicationName() {
        return "Gestione Prenotazioni";
    }
}