package com.example.demo.config;

import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.service.UserService;
import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;


@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return new CustomUserDetailsService(userService);
    }

    @Bean
    public DaoAuthenticationProvider authProvider(PasswordEncoder passwordEncoder,
                                                  UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
//        authProvider.setHideUserNotFoundExceptions(false);
        return authProvider;

}

    @Bean
    public HttpFirewall allowCustomFirewall() {
        return new DefaultHttpFirewall(); // Use DefaultHttpFirewall to bypass StrictHttpFirewall checks
    }


//    DispatcherType.FORWARD: forward đường lời gọi đến file fiew
//    DispatcherType.INCLUDE: cho phép truy cập tới các service ......
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {
        http.authorizeHttpRequests(authorize->authorize
                        .dispatcherTypeMatchers(DispatcherType.FORWARD,
                                DispatcherType.INCLUDE).permitAll()
                        .requestMatchers("/","/login","/client/**","/css/**","/js/**","/img/**").permitAll()
                        .anyRequest().authenticated()

                )

                .formLogin(formLogin->formLogin
                        .loginPage("/login")
                        .failureUrl("/login?error").permitAll());

        return http.build();

    }

}
