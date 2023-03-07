package com.dkhar.sengkur.dao;

import com.dkhar.sengkur.model.*;

import java.util.List;


public interface Doa {
    List<District> getAllDistrict();
    void add(Test_table testtable);

    String saveacademics(Academics academics);
    List<Academics> getAllAcademics();
    List<Dorbar> getAllDorbar();

    String saveUser(Users user);


    Users getUserByPhone(String phone);

}
