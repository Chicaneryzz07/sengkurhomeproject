package com.dkhar.sengkur.controller;

import com.dkhar.sengkur.dao.Doa;
import com.dkhar.sengkur.exception.ResourceNotFoundException;
import com.dkhar.sengkur.model.Academics;
import com.dkhar.sengkur.model.M_Achievements;
import com.dkhar.sengkur.model.T_AllAchievements;
import com.dkhar.sengkur.model.Users;
import com.dkhar.sengkur.repository.AchievementsRepository;
import com.dkhar.sengkur.repository.UserRepository;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDate;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private Doa doa;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AchievementsRepository achievementsRepository;


    @GetMapping("/addachievements")
    public String addachievements(HttpSession httpSession, Model model, Principal principal) {
       // System.out.println("Principal::"+principal.getName());
        //Users users=userRepository.findByEmail(principal.getName());
       // if(users.getRoleid().getRoleid()==1){
            model.addAttribute("achievementsname",doa.getAchievementsName());


            return "addachievements";
    //}
//        else{
//            model.addAttribute("errormessage","You Not Authorized to access this page.");
//            model.addAttribute("title","User Not Authorized");
//            return "error";
//        }
//

    }

    @PostMapping("/saveacademics")
    @ResponseBody
    public String saveacademics(Model model,String achievement, String kyrteng, String field, String details, String dorbar, String date, String venue) {
//        model.addAttribute("name", name);

        T_AllAchievements tAllAchievements = new T_AllAchievements();
        M_Achievements m_achievements=new M_Achievements();


        m_achievements.setAchievement_id(Integer.parseInt(achievement));

        tAllAchievements.setAchievement_id(m_achievements);
        tAllAchievements.setKyrteng(kyrteng);
        tAllAchievements.setField(field);
        tAllAchievements.setDorbar(dorbar);
        tAllAchievements.setDetails(details);
        System.out.println("date-->" + date);
        LocalDate dn = null;
        if (!date.isEmpty()) {
            dn = LocalDate.parse(date);
        }

        tAllAchievements.setSnem(dn);
        tAllAchievements.setVenue(venue);

        String rs = "-1";
        try{
        achievementsRepository.save(tAllAchievements);
        rs="1";
        }
        catch (Exception e){
            e.printStackTrace();
        }


        return rs;
    }

    @GetMapping("/testpage")
    public String getUserByPhone(Model model ) {

        // phone = "1235134123";
       // System.out.println("Phone controller"+phone);
       // model.addAttribute("user", doa.getUserByPhone(phone));
//        return
        return "test_page";
    }
}
