package pattern.skillmatchbackend.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Define the JwtRequestFilter bean here if you haven't already
    @Bean
    public JwtRequestFilter jwtRequestFilter() {
        return new JwtRequestFilter();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable CSRF if you're using JWTs as it is typically not needed
                .authorizeRequests(authorize -> authorize
                        .antMatchers("/login", "/register").permitAll() // Public endpoints
                        .anyRequest().authenticated() // All other endpoints require authentication
                )
                .addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class); // Add your JWT filter

        // You can also configure CORS, session management, etc., here if necessary

        return http.build();
    }
}
