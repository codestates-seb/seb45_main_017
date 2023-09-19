package recipe.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import recipe.server.auth.filter.JwtAuthenticationFilter;
import recipe.server.auth.filter.JwtVerificationFilter;
import recipe.server.auth.handler.MemberAuthenticationFailureHandler;
import recipe.server.auth.handler.MemberAuthenticationSuccessHandler;
import recipe.server.auth.handler.OAuthLoginFailureHandler;
import recipe.server.auth.handler.OAuthLoginSuccessHandler;
import recipe.server.auth.jwt.JwtTokenizer;
import recipe.server.auth.utils.CustomAuthorityUtils;
import recipe.server.member.repository.MemberRepository;
import recipe.server.member.service.MemberService;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;
import java.util.List;


@Configuration
public class SecurityConfiguration {

    private final JwtTokenizer jwtTokenizer;

    private final CustomAuthorityUtils authorityUtils;

    private final MemberRepository memberRepository;

    @Autowired
    OAuthLoginSuccessHandler oAuthLoginSuccessHandler;

    @Autowired
    OAuthLoginFailureHandler oAuthLoginFailureHandler;

    // todo 리팩터링
    @Autowired
    MemberService memberService;

    public SecurityConfiguration(JwtTokenizer jwtTokenizer, CustomAuthorityUtils authorityUtils, MemberRepository memberRepository) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
        this.memberRepository = memberRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .cors(withDefaults())  // Cors 설정 추가
                // Cors 에러시 모든 허용이라 나중에 코드 뺴고 설정추가로 수정해야 될 수도 있어요.
                .cors(configuration -> configuration
                        .configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// 세션 생성 (X)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .apply(new CustomFilterConfigurer())
                .and()
                .authorizeHttpRequests(authorize -> authorize

                        /**
                         * / * 회원 권한 설정.
                         */

                        .antMatchers(HttpMethod.PATCH, "/members/**").hasRole("USER")
                        .antMatchers(HttpMethod.POST, "/recipes").hasRole("USER")
                        .antMatchers(HttpMethod.PATCH, "/recipes/**").hasRole("USER")
                        .antMatchers(HttpMethod.DELETE, "/recipes/**").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.POST, "/recipes/**/comment").hasRole("USER")
                        .antMatchers(HttpMethod.PATCH, "/recipes/**/comment/**").hasRole("USER")

                        .anyRequest().permitAll()


                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE"));
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("*"));
//        configuration.setAllowedHeaders(Arrays.asList("*"));


        UrlBasedCorsConfigurationSource source =new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    // todo 리팩터링
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .loginPage("/login")
                .userInfoEndpoint()
                .userService(memberService)
                .and()
                .successHandler(oAuthLoginSuccessHandler)
                .failureHandler(oAuthLoginFailureHandler);
    }

    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {

        @Override
        public void configure(HttpSecurity builder) throws Exception {

            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer, memberRepository);
            jwtAuthenticationFilter.setFilterProcessesUrl("/login");

            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new MemberAuthenticationSuccessHandler(memberRepository));
            jwtAuthenticationFilter.setAuthenticationFailureHandler(new MemberAuthenticationFailureHandler());

            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);

            builder
                    .addFilter(jwtAuthenticationFilter)
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
        }
    }
}