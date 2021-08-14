package com.myproject.learneng.dao;

import com.myproject.learneng.domain.Role;
import com.myproject.learneng.domain.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJpaRepository extends JpaRepository<Role,Long> {
    Role findByRoleName(RoleName roleName);
}