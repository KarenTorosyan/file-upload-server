package server;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration(proxyBeanMethods = false)
public class WebConfig implements WebMvcConfigurer {

    private final CorsConfiguration corsConfiguration;

    public WebConfig(CorsConfiguration corsConfiguration) {
        this.corsConfiguration = corsConfiguration;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .combine(corsConfiguration);
    }

    @Configuration(proxyBeanMethods = false)
    static class CorsConfig {

        @Bean
        @ConfigurationProperties("cors")
        CorsConfiguration corsConfiguration() {
            return new CorsConfiguration();
        }
    }
}
