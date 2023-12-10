package bg.softuni.strengthstackshop.config;

import bg.softuni.strengthstackshop.util.LoginStatisticInterceptor;
import org.springframework.context.annotation.Configuration;
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
}
