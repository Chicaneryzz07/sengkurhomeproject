package com.dkhar.sengkur.dao;

import com.dkhar.sengkur.model.*;
import com.dkhar.sengkur.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

import java.util.*;

@Service
@Component
@Repository
public class DoaImplementation implements Doa {

    @Autowired
    private EntityManager entityManager;

    // private SessionFactory sessionFactory = null;
    @Override
    public List<District> getAllDistrict() {

        List<District> rs;
        // Session session = sessionFactory.unwrap(Session.class);
        // Session session = sessionFactory.openSession();
        // session.beginTransaction();
        //
        // Query query = session.createQuery("from District order by districtcode");
        // rs = query.list();
        //
        // session.close();
        // Session session=entityManager.unwrap(Session.class);
        Session session = entityManager.unwrap(Session.class);
        // SessionFactory sessionFactory = session.getSessionFactory();

        Query query = session.createQuery("from District where districtcode<>'-1' order by districtcode",
                District.class);

        // Query query=sessionFactory.openSession().createQuery("from District where
        // districtcode<>'-1' order by districtcode");

        rs = query.getResultList();

        // session.close();

        // System.out.println("rs-->"+rs.toString());
        return rs;

    }

    @Override
    public void add(Test_table testtable) {
        Session session = entityManager.unwrap(Session.class);
        // SessionFactory sessionFactory = session.getSessionFactory();
        // sessionFactory.openSession().saveOrUpdate(testtable);
        // session.save(testtable);
        session.saveOrUpdate(testtable);

    }

    @Override
    public String saveacademics(Academics academics) {
        String rs = "-1";
        try {
            Session session = entityManager.unwrap(Session.class);
            // SessionFactory sessionFactory = session.getSessionFactory();

            // sessionFactory.openSession().saveOrUpdate(academics);
            session.saveOrUpdate(academics);
            rs = "1";
        } catch (Exception e) {
            rs = "-1";
        }

        return rs;

    }

    @Override
    public List<Academics> getAllAcademics() {
        List<Academics> a = null;

        Session session = entityManager.unwrap(Session.class);
        // SessionFactory sessionFactory = session.getSessionFactory();

        Query query = session.createQuery("from Academics order by id");
        a = query.getResultList();

        return a;
    }

    @Override
    public List<Dorbar> getAllDorbar() {
        List<Dorbar> a = null;

        Session session = entityManager.unwrap(Session.class);
        // SessionFactory sessionFactory = session.getSessionFactory();

        Query query = session.createQuery("from Dorbar order by dorbarcode");
        a = query.getResultList();

        return a;
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String saveUser(Users user) {

        String rs = "-1";
        // Users user1 = new
        // Users(user.getFirst_name(),user.getMiddle_name(),user.getLast_name(),user.getPhone(),user.getEmail(),
        //
        // passwordEncoder.encode(user.getPassword()), Arrays.asList(new M_roles()));

        Users existingemail = userRepository.findByEmail(user.getEmail());
        Users phone = null;
        phone = userRepository.phone(user.getPhone());

        if (existingemail == null && phone == null) {
            try {
                Session session = entityManager.unwrap(Session.class);
                // SessionFactory sessionFactory = session.getSessionFactory();

                user.setPassword(passwordEncoder.encode(user.getPassword()));
                // user.setRoleid(new M_roles(2));

                session.saveOrUpdate(user);

                rs = "1";
            } catch (Exception e) {
                System.out.println("Exception-->" + e);
                rs = "-1";
            }
        } else if (phone != null)
            rs = "-3";
        else
            rs = "-2";

        return rs;
    }

    @Override
    public Users getUserByPhone(String phone) {

        Users users = null;
        String rs = "-1";
        try {

            Session session = entityManager.unwrap(Session.class);
            // SessionFactory sessionFactory = session.getSessionFactory();

            Query query = session.createQuery("from Users where phone=:phone");
            query.setParameter("phone", phone);
            users = (Users) query.uniqueResult();

            rs = "1";
        } catch (Exception e) {
            rs = "-1";
        }

        return users;
    }

    @Override
    public List<M_Achievements> getAchievementsName() {
        List<M_Achievements> mAchievements;

        Session session = entityManager.unwrap(Session.class);
        // SessionFactory sessionFactory = session.getSessionFactory();

        Query query = session.createQuery("from M_Achievements order by achievement_name");
        // query.setParameter("phone",phone);
        mAchievements = query.getResultList();

        return mAchievements;
    }

}