package com.house.service.user;

import com.house.entity.Role;
import com.house.entity.User;
import com.house.repository.RoleRepository;
import com.house.repository.UserRepository;
import com.house.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User findUserByName(String name) {
        User user = userRepository.findByName(name);
        if (user == null){
            return null;
        }

        List<Role> roles = roleRepository.queryRolesByUserId(user.getId());
        if (roles == null ||roles.isEmpty()){
            throw new DisabledException("权限非法");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        roles.forEach(role -> grantedAuthorities
                .add(new SimpleGrantedAuthority("ROLE_"+
                role.getName())));
        user.setAuthorityList(grantedAuthorities);
        return user;
    }
}
