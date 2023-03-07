/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dkhar.sengkur.model;

/**
 *
 * @author ASUS
 */

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "test_table")
public class Test_table implements Serializable {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name="first_name")
    private String firstname;

    @Column(name="last_name")
    private String lastname;

    @Column(name="email")
    private String email;



}
