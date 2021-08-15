package com.example.network.framework.security;

import com.example.network.domain.UserInfo;
import com.example.network.framework.jwt.JwtUser;
import com.example.network.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @program com.example.network.framework.security
 * @description 系统用户登录验证对象
 * @auther Mr.Xiong
 * @create 2021-05-13 21:45:39
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserInfo user = userService.queryUserByAccount(userName);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用戶不存在");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

        return new JwtUser(user);
    }

}
