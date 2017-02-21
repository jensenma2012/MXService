package com.xiaoma.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.xiaoma.entity.pojo.Admin;
import com.xiaoma.entity.pojo.Role;
import com.xiaoma.mybatis.mapper.AdminMapper;

public class AdminDetailsServiceImpl implements UserDetailsService {

    @Resource
    private AdminMapper adminMapper;

    @Override
    public Admin loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = null;
        try {
            admin = adminMapper.queryByUsername(username);
        } catch (Exception e) {
            throw new UsernameNotFoundException("管理员[" + username + "]信息获取失败!");
        }

        if (admin == null) {
            throw new UsernameNotFoundException("管理员[" + username + "]不存在!");
        }
        admin.setAuthorities(getAuthorities(admin));
        return admin;
    }

    public List<GrantedAuthority> getAuthorities(Admin admin) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (Role role : admin.getRoles()) {
            for (String s : role.getAuthorities()) {
                GrantedAuthority authoritiy = new SimpleGrantedAuthority(s);
                authorities.add(authoritiy);
            }
        }
        return authorities;
    }

}
