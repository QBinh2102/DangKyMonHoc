/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqb.DangKyMonHoc.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tqb.DangKyMonHoc.filters.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author toquocbinh2102
 */
@Configuration
public class SecurityConfig {
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary
                = new Cloudinary(ObjectUtils.asMap(
                        "cloud_name", "dbhhpljbo",
                        "api_key", "769838993333676",
                        "api_secret", "sKhPxCraBaikLWgXkceg2nwZox8",
                        "secure", true));
        
        return cloudinary;
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(withDefaults())
            .csrf(csrf -> csrf.disable()) // Tắt CSRF vì API không dùng cookie
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Không dùng session
            .authorizeHttpRequests(requests -> requests
                .requestMatchers("/api/secure/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/secure/me/**").hasRole("SINH_VIEN")
                .requestMatchers("/api/secure/**").authenticated()
                .requestMatchers("/api/login", "/api/register").permitAll()
                .requestMatchers("/api/**").permitAll()
                .requestMatchers("/js/**").permitAll()
                .anyRequest().permitAll()
            )
            .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class); // Thêm filter xử lý JWT

        return http.build();
    }
}
