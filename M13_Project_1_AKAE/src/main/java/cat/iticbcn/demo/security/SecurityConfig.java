package cat.iticbcn.demo.security;
import cat.iticbcn.demo.bean.UserAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public AuthenticationManager authManager(HttpSecurity http,
                                             PasswordEncoder passwordEncoder,
                                             UserDetailsService userDetailsService) throws Exception {

        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and().build();

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        RequestMatcher rqstAuthoritation = new AntPathRequestMatcher("/auth/**");

        RequestMatcher getOffers = new AntPathRequestMatcher("/offers/**", HttpMethod.GET.name());
        RequestMatcher postOffers = new AntPathRequestMatcher("/offers/**", HttpMethod.POST.name());
        RequestMatcher putOffers = new AntPathRequestMatcher("/offers/**", HttpMethod.PUT.name());
        RequestMatcher deleteOffers = new AntPathRequestMatcher("/offers/**", HttpMethod.DELETE.name());

        RequestMatcher getCompanies = new AntPathRequestMatcher("/companies/**", HttpMethod.GET.name());
        RequestMatcher postCompanies = new AntPathRequestMatcher("/companies/**", HttpMethod.POST.name());
        RequestMatcher putCompanies = new AntPathRequestMatcher("/companies/**", HttpMethod.PUT.name());
        RequestMatcher deleteCompanies = new AntPathRequestMatcher("/companies/**", HttpMethod.DELETE.name());

        RequestMatcher studentRequests = new AntPathRequestMatcher("/student/**");

        http.authorizeRequests().requestMatchers(rqstAuthoritation).permitAll();

        http.authorizeRequests().requestMatchers(getOffers).hasAuthority(UserAuthority.READ.toString());
        http.authorizeRequests().requestMatchers(postOffers).hasAuthority(UserAuthority.WRITE.name());
        http.authorizeRequests().requestMatchers(putOffers).hasAuthority(UserAuthority.WRITE.name());
        http.authorizeRequests().requestMatchers(deleteOffers).hasAuthority(UserAuthority.WRITE.name());

        http.authorizeRequests().requestMatchers(getCompanies).hasAuthority(UserAuthority.READ.toString());
        http.authorizeRequests().requestMatchers(postCompanies).hasAuthority(UserAuthority.WRITE.name());
        http.authorizeRequests().requestMatchers(putCompanies).hasAuthority(UserAuthority.WRITE.name());
        http.authorizeRequests().requestMatchers(deleteCompanies).hasAuthority(UserAuthority.WRITE.name());

        http.authorizeRequests().requestMatchers(studentRequests).hasAuthority(UserAuthority.READ.name());

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
