package bg.softuni.strengthstackshop.config;

import bg.softuni.strengthstackshop.util.LoginStatisticInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final LoginStatisticInterceptor loginStatisticInterceptor;

    public WebMvcConfig(LoginStatisticInterceptor loginStatisticInterceptor) {
        this.loginStatisticInterceptor = loginStatisticInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginStatisticInterceptor);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply to all endpoints
                .allowedOrigins("http://localhost:8080") // The origin your front-end is served from
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allowed methods
                .allowedHeaders("Content-Type", "Authorization", "X-Requested-With", "Accept", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers") // Specific allowed headers
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials") // Headers to expose to the client
                .allowCredentials(true) // Credentials allowed (cookies, authorization headers, etc.)
                .maxAge(3600); // Max age for the browser to cache the preflight response
    }

}
