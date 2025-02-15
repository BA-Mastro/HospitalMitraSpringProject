package com.binary.myhospital.configs;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private AuthenticationProvider authenticationProvider;
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(AuthenticationProvider authenticationProvider, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csfr -> csfr.disable())
                .cors(cors -> cors.configurationSource(corFilter()))  // set rules who can access the urls
                .authorizeHttpRequests(ahr ->
                                ahr
//                                        .anyRequest().permitAll() // disable security
                                .requestMatchers(HttpMethod.GET,"/api/doctor/all", "/api/department/all", "/api/doctor/*", "/api/department/*","/api/department/{id}/with-doctors")
                                .permitAll()
                                .requestMatchers(HttpMethod.POST,"/api/users/", "/api/users/login")
                                .permitAll()
                                .requestMatchers(HttpMethod.POST,"/api/doctor/create", "/api/department/create")
                                .authenticated()
                                .requestMatchers(HttpMethod.PUT, "/api/doctor/*", "/api/department/*")
                                .hasAnyRole("DEPARTMENT", "ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/doctor/*", "/api/department/*")
                                .hasAnyRole("DEPARTMENT", "ADMIN")
                )
                .sessionManagement(ses -> {
                    ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
////                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
    //    we added this method for our react project to have access to our urls
    @Bean
    public UrlBasedCorsConfigurationSource corFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(false);
        configuration.setAllowedOrigins(List.of("http://localhost:5173", "http://127.0.0.1:5173"));
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
//this method hardcodes the roles, so we dont use it
//    we give this responsibility and flexibility to the userCredential using the method grantedAuthority
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("userPass"))
//                .roles("USER")
//                .build();
//        UserDetails department = User.builder()
//                .username("department")
//                .password(passwordEncoder().encode("departmentPass"))
//                .roles("department")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("adminPass"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(admin, department, user);
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }


}