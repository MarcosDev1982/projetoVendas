package com.example.marcosvendas.config;

import com.example.marcosvendas.security.JWTAuthenticationFilter;
import com.example.marcosvendas.security.JWTAuthorizationFilter;
import com.example.marcosvendas.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private Environment environment;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    private static final String[] PUBLIC_MATCHERS_GET = {
            "/h2-console/**",
            "/produtos/**",
            "/categorias/**"
    };

    private static final String[] PUBLIC_MATCHERS_POST = {
            "/clientes/**"
    };

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        if (Arrays.asList(environment.getActiveProfiles()).contains("test")) {
            httpSecurity.headers().frameOptions().disable();
        }
        httpSecurity.cors().and().csrf().disable();
        httpSecurity.authorizeHttpRequests()
                .antMatchers(PUBLIC_MATCHERS_GET).permitAll()
                .antMatchers(PUBLIC_MATCHERS_POST).permitAll()
                .anyRequest().authenticated();
        httpSecurity.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
        httpSecurity.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));

        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Override
    public void configure(AuthenticationManagerBuilder aut) throws Exception {
        aut.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
