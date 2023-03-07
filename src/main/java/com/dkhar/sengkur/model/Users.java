package com.dkhar.sengkur.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;


@Data
@Entity
@Table(name = "m_users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userid;
    @Column
    private String first_name;
    @Column
    private String middle_name;

    @Column
    private String last_name;
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private String password;
    @Column(name = "reset_password_token")
    private String resetPasswordToken;


    @ManyToOne
    @JoinColumn(name = "roleid",referencedColumnName = "roleid")
    private M_roles roleid;


   // @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "users_roles",
//            joinColumns =@JoinColumn(
//                    name = "user_id", referencedColumnName = "userid"),
//            inverseJoinColumns = @JoinColumn(
//                    name = "role_id",referencedColumnName = "roleid"
//            )
//    )
//    private Collection<M_roles> roles;



}