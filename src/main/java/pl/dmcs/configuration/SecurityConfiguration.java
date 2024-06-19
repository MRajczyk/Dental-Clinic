package pl.dmcs.configuration;

import jakarta.annotation.Resource;
import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import pl.dmcs.utils.CustomAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Resource(name="myAppUserDetailsService")
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        // for database users
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);

        http
                .authorizeHttpRequests((authz) -> authz
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers("/appUsers*").hasAnyRole("ADMIN")
                        .requestMatchers("/visits*").hasAnyRole("ADMIN")
                        .requestMatchers("/procedures*").hasAnyRole("ADMIN")
                        // USER ROUTES
                        .requestMatchers("/user*").hasAnyRole("USER")
                        .requestMatchers("/pastVisit*").hasAnyRole("USER")
                        .requestMatchers("/pastVisits*").hasAnyRole("USER")
                        .requestMatchers("/scheduleVisit*").hasAnyRole("USER")
                        .requestMatchers("/scheduledVisits*").hasAnyRole("USER")
                        // UNRESTRICTED ROUTES
                        .requestMatchers("/proceduresRest/all").permitAll()
                        .requestMatchers("/proceduresRest/all.xml").permitAll()
                        .requestMatchers("/login*").anonymous()
                        .requestMatchers("/register*").anonymous()
                        .requestMatchers("/activate").anonymous()
                        .requestMatchers("/registerAppUser*").anonymous()
                        .anyRequest().authenticated()
                )
                // login with default login page
//                  .formLogin(form -> form
//                          .permitAll()
//                          )
                // login with custom login page
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("login")
                        .passwordParameter("password")
                        .failureUrl("/login?error")
                        .successHandler(customAuthenticationSuccessHandler)
//                        .defaultSuccessUrl("/",true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        )
                .exceptionHandling(accessDenied -> accessDenied
                        .accessDeniedPage("/accessDenied")
                )
                .httpBasic();
        return http.build();
    }
}













