package pkg1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
          .csrf().disable()
          .authorizeHttpRequests(auth -> auth
              .requestMatchers("/api/teacher/**").hasRole("TEACHER")
              .requestMatchers("/api/student/**").hasRole("STUDENT")
              .anyRequest().authenticated()
          )
          .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails teacher = User.withDefaultPasswordEncoder()
            .username("teacher1")
            .password("password1")
            .roles("TEACHER")
            .build();

        UserDetails student = User.withDefaultPasswordEncoder()
            .username("student1")
            .password("password2")
            .roles("STUDENT")
            .build();

        return new InMemoryUserDetailsManager(teacher, student);
    }
}
