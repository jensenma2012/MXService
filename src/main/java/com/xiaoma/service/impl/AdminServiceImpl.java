package com.xiaoma.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.xiaoma.entity.pojo.Admin;
import com.xiaoma.entity.pojo.Role;
import com.xiaoma.mybatis.mapper.AdminMapper;
import com.xiaoma.mybatis.mapper.BaseMapper;
import com.xiaoma.service.AdminService;

@Service("adminService")
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements UserDetailsService, AdminService {

    private static final String SALT = "admin";

    @Resource
    private ShaPasswordEncoder passwordEncoder;

    @Resource
    @Override
    public void setMapper(BaseMapper<Admin> mapper) {
        super.setMapper(mapper);
    }

    @Override
    public Admin loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = null;
        try {
            admin = ((AdminMapper) this.getMapper()).queryByUsername(username);
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

    @Override
    public Admin getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        } else {
            return (Admin) authentication.getPrincipal();
        }
    }

    @Override
    public boolean validatePassword(String password) {
        Admin currentUser = getCurrentUser();
        if (currentUser == null) {
            return false;
        } else {
            return passwordEncoder.isPasswordValid(currentUser.getPassword(), password, SALT);
        }
    }

    @Override
    public void resetPassword(String newPassword) throws Exception {
        Admin currentUser = getCurrentUser();
        if (currentUser == null) {
            return;
        } else {
            currentUser.setPassword(encodePassword(newPassword));
            update(currentUser);
        }
    }

    private String encodePassword(String password) {
        return passwordEncoder.encodePassword(password, SALT);
    }

}
