package com.dkhar.sengkur.service;

import com.dkhar.sengkur.dao.Doa;
import com.dkhar.sengkur.model.M_roles;
import com.dkhar.sengkur.model.Users;
import com.dkhar.sengkur.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
//@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

//    public UserServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users users=userRepository.findByEmail(username);
        if(users==null)
            throw new UsernameNotFoundException("Username not found");

        //System.out.println("user roles:::"+maproles(users.getRoleid()).toString());

        return new User(users.getEmail(),users.getPassword(),maproles(users.getRoleid()));

    }

    //if roles is many to many
    private Collection<? extends GrantedAuthority>maproles(Collection<M_roles> roles){

       return roles.stream().map(role->new SimpleGrantedAuthority(role.getRolename())).collect(Collectors.toList());
    }

    private Collection<? extends GrantedAuthority>maproles(M_roles roles){
            Collection<M_roles> m=new ArrayList<>();
            m.add(roles);


        return m.stream().map(role->new SimpleGrantedAuthority(role.getRolename())).collect(Collectors.toList());
    }

    //reset password

    @Autowired
    Doa doa;
    public void updateResetPasswordToken(String token, String email) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(email);
        if (user != null) {
            user.setResetPasswordToken(token);
            userRepository.save(user);
           // doa.saveandUpdate(user);
        } else {
            throw new UsernameNotFoundException("Could not find any user with the given email " + email);
        }
    }

    public Users getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token);
    }



    public void updatePassword(Users user, String newPassword) {
        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(newPassword));
        //user.setPassword(encodedPassword);
        user.setResetPasswordToken(null);
        doa.saveUser(user);
    }



}
