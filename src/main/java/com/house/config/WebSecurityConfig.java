package com.house.config;

import com.house.security.AuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/login").permitAll()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/user/login").permitAll()
        .antMatchers("/admin/**").hasRole("ADMIN")
        .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
        .and()
        .formLogin().loginProcessingUrl("/login")
        .and();

        //防御策略
        http.csrf().disable();
        //开启同源， Hui使用iframe开发的前端框架
        http.headers().frameOptions().sameOrigin();
    }

    /**
     * 自定义认证策略
     * 只能有一个AuthenticationManagerBuilder的实例注入
     */
    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN").and();
//        auth.authenticationProvider(authProvider()).eraseCredentials(true);
    }

    public AuthProvider authProvider(){
        return new AuthProvider();
    }
}
