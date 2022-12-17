package com.tdtu.finalproject;


import com.tdtu.finalproject.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


public class UserDetail implements UserDetails {
    private User user;

    public UserDetail(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ADMIN");
        //Trả về một List các quyền của ADMIN
        return List.of(authority);
    }

    //implement phương thức trả về mật khẩu của Userdeteails
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    //implement phương thức trả về usename của UserDetails
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    //implement phương thức trả về liệu tài khoản user đã hết hạn chưa của UserDetails
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //implement phương thức trả về liệu user có bị khoá của UserDetails
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //implement phương thức trả về liệu mật khẩu của user có hết hạn hay chưa của UserDetails
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //implement phương thức trả về user có được xác thực tài khoản để được truy cập hệ thống của UserDetails
    @Override
    public boolean isEnabled() {
        return true;
    }

}