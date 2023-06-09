package kr.pe.afterschool.global.security;

import kr.pe.afterschool.global.filter.FilterConfig;
import kr.pe.afterschool.global.filter.JwtTokenFilter;
import kr.pe.afterschool.global.lib.JsonParser;
import kr.pe.afterschool.global.security.handler.CustomAccessDeniedHandler;
import kr.pe.afterschool.global.security.handler.CustomAuthenticationEntryPoint;
import kr.pe.afterschool.global.security.jwt.JwtTokenParser;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtTokenParser jwtTokenParser;
    private final JsonParser jsonParser;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors();
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .authorizeRequests()
                .antMatchers("/survey").authenticated()
                .antMatchers(HttpMethod.GET, "/apply").authenticated()
                .antMatchers(HttpMethod.POST, "/apply").authenticated()
                .antMatchers(HttpMethod.POST, "/school", "/classroom", "/apply/decision").hasAnyRole("TEACHER", "ADMIN")
                .antMatchers(HttpMethod.PATCH, "/school", "/classroom", "/apply/decision").hasAnyRole("TEACHER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/school", "/classroom").hasAnyRole("TEACHER", "ADMIN")
                .antMatchers("/auth").permitAll();
        http
                .apply(new FilterConfig(jwtTokenParser, jsonParser));
        http
                .addFilterBefore(new JwtTokenFilter(jwtTokenParser), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                .accessDeniedHandler(customAccessDeniedHandler);
        return http.build();
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration  = new CorsConfiguration();

        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
