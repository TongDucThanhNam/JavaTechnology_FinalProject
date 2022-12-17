package com.tdtu.finalproject;

import com.tdtu.finalproject.model.User;
import com.tdtu.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Lấy thông tin người dùng từ CSDL bằng username
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            //Trả về ngoại lệ khu không tìm thấy user trong CSDL
            throw new UsernameNotFoundException("Không tìm thấy user");
        }
        return new UserDetail(user);
    }
}
