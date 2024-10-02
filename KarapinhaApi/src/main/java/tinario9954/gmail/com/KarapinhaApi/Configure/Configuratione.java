package tinario9954.gmail.com.KarapinhaApi.Configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Configuratione {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
                return httpSecurity
                                .csrf(csrf -> csrf.disable()) // Desativa CSRF para permitir requisições POST sem CSRF
                                                              // tokens
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**",
                                                                "/swagger-ui.html",
                                                                "/swagger-resources/**", "/webjars/**")
                                                .permitAll()
                                                .requestMatchers("/", "/home", "/register", "/users", "/login")
                                                .permitAll() // Permite acesso ao login e outras rotas públicas
                                                .anyRequest().authenticated() // Exige autenticação para outras rotas
                                )
                                .formLogin(form -> form.disable()) // Desativa o formulário de login padrão do Spring
                                .httpBasic(Customizer.withDefaults()) // Adiciona suporte a autenticação HTTP Basic se
                                                                      // necessário
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Torna a
                                                                                                         // sessão
                                                                                                         // stateless
                                                                                                         // para APIs
                                .build();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
                        throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }

        @Bean(name = "customPasswordEncoder")
        public PasswordEncoder customPasswordEncoder() {
                return new BCryptPasswordEncoder();
        }
}