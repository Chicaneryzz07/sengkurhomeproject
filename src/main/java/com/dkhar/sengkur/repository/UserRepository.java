package com.dkhar.sengkur.repository;

import com.dkhar.sengkur.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;



@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);


    Users phone(String phone);

    Users findByResetPasswordToken(String token);

   Users save(Users u);


}
