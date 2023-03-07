package com.dkhar.sengkur.repository;

import com.dkhar.sengkur.model.M_roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

//@Service
//@Component
@Repository
public interface RoleRepository extends JpaRepository<M_roles, Long> {
    M_roles findByRolename(String name);
}
