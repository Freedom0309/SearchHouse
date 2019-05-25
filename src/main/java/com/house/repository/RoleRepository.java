package com.house.repository;

import com.house.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> queryRolesByUserId(Long userId);
}
