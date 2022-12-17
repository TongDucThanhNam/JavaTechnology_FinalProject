package com.tdtu.finalproject;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //Hàm tạo 1 đối tượng MyUserDetailService để quản lý thông tin của user.
    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailService();
    }

    //Hàm tạo một đối tượng để mã hoá - kiem tra mat khau Bcrypt
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Hàm cung cấp xác thực người dùng
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        //Tạo đối tượng DaoAuthenticationProvider
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        //Cung cấp cho DaoAuthenticationProvider UserdetailService và Password encoder
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        //Trả vê
        return authProvider;
    }

    ////Cấu hình xác thực
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    //Cấu hình HTTP
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/quanlytaikhoan", "/quanlysanbong").authenticated() //Phải đăng nhập mới truy cập được vào 2 trang này
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .usernameParameter("username")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/").permitAll();
    }
}