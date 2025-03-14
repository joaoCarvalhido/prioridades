package br.com.prioridades.config;

import br.com.prioridades.service.impl.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailService userDetailService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/usuarios/login", "/usuarios/cadastro",
                                "/css/**", "/js/**", "/img/**",
                                "/materialize/**", "/default/**")
                        .permitAll()
                        .anyRequest().authenticated())

        .formLogin(form -> form
                .loginPage("/usuarios/login")
                .loginProcessingUrl("/usuarios/processa-login")
                .defaultSuccessUrl("/prioridades/home", true)
                .failureForwardUrl("/usuarios/login?error=true")
                .permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
