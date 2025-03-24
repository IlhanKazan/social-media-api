package org.example.socialmediaapi.config;

import org.example.socialmediaapi.security.JwtTokenFilter;
import org.example.socialmediaapi.service.impl.AccountDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${allowed.origins}")
    private String[] allowedOrigins;

    private final AccountDetailsServiceImpl accountDetailsServiceImpl;
    private final String secretKey = "ilhan_secret_key";

    public SecurityConfig(AccountDetailsServiceImpl accountDetailsServiceImpl) {
        this.accountDetailsServiceImpl = accountDetailsServiceImpl;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // CORS yapılandırması
                .csrf(csrf -> csrf.disable()) // CSRF'yi devre dışı bırak
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Oturum yönetimi
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/auth/login", "/auth/signup").permitAll() // Public endpoint'ler
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // OPTIONS isteklerine izin ver
                        .anyRequest().authenticated() // Diğer tüm istekler için kimlik doğrulama gerekli
                )
                .addFilterBefore(new JwtTokenFilter(secretKey, accountDetailsServiceImpl), UsernamePasswordAuthenticationFilter.class); // JWT filtresi ekle
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(allowedOrigins)); // İzin verilen origin'ler
        configuration.setAllowedMethods(Arrays.asList("GET", "POST")); // İzin verilen HTTP metodları
        configuration.setAllowedHeaders(Arrays.asList("*")); // İzin verilen header'lar
        configuration.setAllowCredentials(true); // Kimlik bilgilerine izin ver (örneğin, cookie'ler)
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Tüm endpoint'ler için CORS'u etkinleştir
        return source;
    }

}
