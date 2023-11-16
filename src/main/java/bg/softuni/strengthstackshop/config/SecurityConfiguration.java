package bg.softuni.strengthstackshop.config;

import bg.softuni.strengthstackshop.model.enums.RoleName;
import bg.softuni.strengthstackshop.repository.UserRepository;
import bg.softuni.strengthstackshop.service.impl.StrengthStackShopUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(
                authorizeRequests -> authorizeRequests
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/", "/user/login", "/user/register", "/user/login-error").permitAll()
                        .requestMatchers("/product-add").hasRole(RoleName.ADMIN.name())
                        .anyRequest().authenticated()
        ).formLogin(
                formLogin -> {
                    formLogin
                            .loginPage("/user/login")
                            .usernameParameter("username")
                            .passwordParameter("password")
                            .defaultSuccessUrl("/home")
                            .failureForwardUrl("/user/login-error");
                }
        ).logout(
                logout -> {
                    logout
                            .logoutUrl("/user/logout")
                            .logoutSuccessUrl("/user/login")
                            .invalidateHttpSession(true);
                }
        );

        //todo: remember me!

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return new StrengthStackShopUserDetailsService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
