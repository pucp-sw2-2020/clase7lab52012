package pe.pucp.sw2.clase7lab52012.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage("/")
                .loginProcessingUrl("/ingresar")
                .usernameParameter("correo")
                .defaultSuccessUrl("/redirectByRol");

        //el tiempo esta en segundos
        http.rememberMe().tokenValiditySeconds(86400).rememberMeParameter("recordar");

        http.authorizeRequests().antMatchers("/user","/user/**").hasAnyAuthority("user","admin");
        http.authorizeRequests().anyRequest().permitAll();
    }

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery("SELECT correo,password,enabled FROM usuarios where correo = ?")
                .authoritiesByUsernameQuery("SELECT u.correo, r.nombre FROM usuarios u\n" +
                        "INNER JOIN rolxusuario rxu ON (u.idusuario = rxu.idusuario)\n" +
                        "INNER JOIN rol r ON (rxu.idrol = r.idrol)\n" +
                        "where u.correo = ? and enabled = 1");

    }
}
