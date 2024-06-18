package com.chy.util.security;

import com.chy.entity.Permission;
import com.chy.entity.Role;
import com.chy.entity.User;
import com.chy.mapper.PermissionMapper;
import com.chy.mapper.RoleMapper;
import com.chy.mapper.UserMapper;
import com.chy.util.exception.NewException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new NewException(401, "该用户不存在");
        }

        //List<Role> roles = roleMapper.findRolesByUserId(user.getId());
        List<Permission> permissions = permissionMapper.findPermissionsByUserId(user.getId());

        List<SimpleGrantedAuthority> authorities = permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
