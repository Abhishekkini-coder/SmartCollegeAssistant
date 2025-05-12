package pkg1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
          // we’re not using CSRF tokens in this demo setup
          .csrf().disable()

          // 1) public paths: landing page, login/signup pages, static assets
          .authorizeHttpRequests(auth -> auth
              .requestMatchers(
                  "/", "/index.html",    // your landing page
                  "/login.html",         // login form
                  "/register.html",      // (or your register.html)
                  "student-dashboard.html",
                  "attendance.html",
                  "gradebook.html",
                 "todo.html",
                  "/css/**", "/js/**", "/images/**"
              ).permitAll()
              // 2) panel‐specific paths
              .requestMatchers("/api/teacher/**").hasRole("TEACHER")
              .requestMatchers("/api/student/**").hasRole("STUDENT")
              // 3) anything else requires authentication
              .anyRequest().authenticated()
          )

          // 4) form-based login configuration
          .formLogin(form -> form
              .loginPage("/login.html")        // GET this HTML
              .loginProcessingUrl("/login")    // POST handler
              .defaultSuccessUrl("/student-dashboard.html", true)
              .permitAll()
          )

          // 5) logout configuration
          .logout(logout -> logout
              .logoutUrl("/logout")
              .logoutSuccessUrl("/login.html?logout")
              .permitAll()
          );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // In-memory users for demo
        UserDetails teacher = User.withUsername("teacher1")
            .password(passwordEncoder().encode("password1"))
            .roles("TEACHER")
            .build();

        UserDetails student = User.withUsername("student1")
            .password(passwordEncoder().encode("password2"))
            .roles("STUDENT")
            .build();

        return new InMemoryUserDetailsManager(teacher, student);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
    protected void configure(HttpSecurity http) throws Exception {
        http
          .csrf().disable()           
          .authorizeRequests()
            .requestMatchers("/attendance/**").permitAll()   // allow your attendance API
            .requestMatchers("/api/student/**").hasRole("STUDENT")
            .requestMatchers("/api/teacher/**").hasRole("TEACHER")
            .anyRequest().authenticated()
          .and()
          .formLogin().disable()       // disable the default login form for API
          .httpBasic();                // or JWT filter, or no auth at all
    }

}
