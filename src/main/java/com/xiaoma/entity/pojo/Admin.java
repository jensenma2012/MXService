package com.xiaoma.entity.pojo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.xiaoma.entity.annotation.FieldDesc;

@Entity
@Table(name = "admin")
public class Admin extends BasePojo implements UserDetails {

    private static final long serialVersionUID = -2860423937656886413L;

    @FieldDesc(name = "username", desc = "用户名")
    @Column(name = "username", nullable = false, length = 64)
    private String username;

    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Column(name = "is_account_non_expired", nullable = false)
    private boolean accountNonExpired;

    @Column(name = "is_account_non_locked", nullable = false)
    private boolean accountNonLocked;

    @Column(name = "is_account_enabled", nullable = false)
    private boolean enabled;

    @Column(name = "is_credentials_non_expired", nullable = false)
    private boolean credentialsNonExpired;

    @FieldDesc(name = "role.name", desc = "权限")
    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Transient
    private List<GrantedAuthority> authorities;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "Admin [id=" + super.getId() + ", username=" + username + ", password=" + password + ", accountNonExpired=" + accountNonExpired + ", accountNonLocked=" + accountNonLocked + ", enabled=" + enabled + ", credentialsNonExpired=" + credentialsNonExpired + ", role=" + role + ", authorities=" + authorities + ", createDate=" + super.getCreateDate() + "]";
    }

}
