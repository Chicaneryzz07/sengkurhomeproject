package com.dkhar.sengkur.controller;

import com.dkhar.sengkur.dao.Doa;
import com.dkhar.sengkur.model.*;
import com.dkhar.sengkur.repository.AchievementsRepository;
import com.dkhar.sengkur.repository.UserRepository;
import com.dkhar.sengkur.service.UserServiceImpl;
import com.dkhar.sengkur.service.Utility;


import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;


@Controller
public class MainController {
    @Autowired
    private Doa doa;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AchievementsRepository achievementsRepository;

    @GetMapping("/")
    public String getHome() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index(Model model) {
//        model.addAttribute("name", name);
        model.addAttribute("title", "Home");

        return "index";
    }

    //Getting parameters from views
//    @GetMapping("/about")
//    public String about(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
//        model.addAttribute("title","Error 404");
//
//        return "about";
//    }
    @GetMapping("/about")
    public String about(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("title", "About");

        return "about";
    }

    @GetMapping("/register")
    public String register( Model model) {
        model.addAttribute("title", "Register");
        model.addAttribute("activesetting", "active_reg");

        return "register";
    }

    @GetMapping("/reports")
    public String reports(Model model) {
        model.addAttribute("title", "Reports");
        model.addAttribute("activesetting", "active_rep");

        return "reports";
    }

    @GetMapping("/activities")
    public String activities(Model model) {
        model.addAttribute("title", "Activities");

        return "activities";
    }

    @GetMapping("/form1")
    public String form1(Model model) {
        model.addAttribute("title", "Form I");
        return "form1";
    }

    @GetMapping("/form2")
    public String form2(Model model) {
        model.addAttribute("title", "Form II");
        return "form2";
    }

    @GetMapping("/academics")
    public String academics(Model model) {

        model.addAttribute("academics", achievementsRepository.findByAchievement_Id(1));
        model.addAttribute("title", "Academics");
        model.addAttribute("activesetting", "active_ach");
        return "academics";
    }

    @GetMapping("/entrepreneurship")
    public String entrepreneurship(Model model) {
        model.addAttribute("title", "Entrepreneurship");
        model.addAttribute("activesetting", "active_ach");

        return "entrepreneurship";
    }

    @GetMapping("/endowment")
    public String endowment(Model model) {
        model.addAttribute("title", "Endowment");
        model.addAttribute("activesetting", "active_rep");

        return "endowment";
    }

    @GetMapping("/awards")
    public String awards(Model model) {
        model.addAttribute("title", "Awards");
        model.addAttribute("activesetting", "active_ach");

        return "awards";
    }

    @GetMapping("/felicitations")
    public String felicitations(Model model) {
        model.addAttribute("title", "Felicitations");
        model.addAttribute("activesetting", "active_ach");

        return "felicitations";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Login");
        return "login";
    }
//    @GetMapping("/logout")
//    public String logout(Model model) {
////        model.addAttribute("name", name);
////        return "login?logout";
//        return "redirect:/login?logout";
//    }


    //    @GetMapping("/testdb")
//    @ResponseBody
    @RequestMapping(value = "/testdb", method = RequestMethod.GET)

    public String test_db(Model model) {
//        model.addAttribute("name", name);
        List<District> x = null;
        try {
            x = doa.getAllDistrict();


        } catch (Exception e) {
            System.out.println("exception-->" + e);
        }
        model.addAttribute("districts", x);

        return "test_db";
    }

//    @GetMapping("/testdb")
//    public String getAllTutorials(@RequestParam(required = false) String districtname,Model model) {
//        try {
//            List<District> tutorials = new ArrayList<District>();
//
//
//                //districtRepo.findByTitleContaining(districtname).forEach(tutorials::add);
//            districtRepo.findAll();
//
//
//            //return new ResponseEntity<>(tutorials, HttpStatus.OK);
//        } catch (Exception e) {
//            //return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        model.addAttribute("districtname", districtRepo.findAll());
//        return "test_db";
//    }

//    @PostMapping("/add")
//    public String add( Model model) {
////        model.addAttribute("name", name);
//        Test_table t=new Test_table();
//        t.setFirstname("micha");
//        t.setLastname("lastname");
//        t.setEmail("email.com");
//
//        doa.add(t);
//        return "index";
//    }

//    @GetMapping("/add")
//    public String add1(Model model) {
////        model.addAttribute("name", name);
//        Test_table t = new Test_table();
//        t.setFirstname("micha");
//        t.setLastname("lastname");
//        t.setEmail("email.com");
//
//        doa.add(t);
//        return "index";
//    }




    @PostMapping(value = "/saveregistration")
    public String saveregistration(Model model, @ModelAttribute("userdetails") Users user) {

        String rs = "0";
//        model.addAttribute("name", name);

        user.setRoleid(new M_roles(2));
        rs = doa.saveUser(user);
//        try{
//        userRepository.save(user);
//            rs="1";
//        }catch (Exception e){
//            System.out.println("Save registration:"+e);
//        }
        //System.out.println("rs->" + rs);

        if (rs.equals("1"))
            return "redirect:/register?success";
        else if (rs.equals("-2")) {
            return "redirect:/register?emailexist";
        } else if (rs.equals("-3")) {
            return "redirect:/register?phoneexist";
        }
        return "redirect:/register?error";
    }



    @GetMapping("/forgotpassword")
    public String forgotpassword(Model model) {

        // phone = "1235134123";
        // System.out.println("Phone controller"+phone);
//        model.addAttribute("user", doa.getUserByPhone(phone));
//        return
        model.addAttribute("title", "Forgot Password");

        return "forgot_password_form";
    }

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/forgotpassword")
    public String processForgotPasswordForm(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");


        String token = RandomString.make(45);
        System.out.println("Token:" + token);
        System.out.println("email:" + email);
        try {
            userService.updateResetPasswordToken(token, email);
            //generate reset password link
            String resetpasswordlink = Utility.getSiteURL(request) + "/reset_password?token=" + token;

            //System.out.println("resetpasswordlink:"+resetpasswordlink);

            //send email
            sendEmail(email, resetpasswordlink);

        } catch (Exception e) {
            model.addAttribute("error1", e.getMessage());
            System.out.println("Exception::" + e);
            return "forgot_password_form";

        }
        //model.addAttribute("success","The reset password link has been sent to your email.");

        return "redirect:/forgotpassword?success";
    }

    @GetMapping("/reset_password")
    public String reset_password(@Param(value = "token") String token, Model model) {
        Users user = userRepository.findByResetPasswordToken(token);
        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "reset_password_form";
        }
        model.addAttribute("token", token);
        model.addAttribute("title", "Reset Password");


        return "reset_password_form";
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/reset_password")
    public String change_password(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String pass = request.getParameter("confirm_password");
        Users user = userRepository.findByResetPasswordToken(token);
        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "reset_password_form";
        } else {
            user.setPassword(passwordEncoder.encode(pass));
            user.setResetPasswordToken(null);
            userRepository.save(user);
            model.addAttribute("success", "You have successfully changed your password");
        }


        return "reset_password_form";
    }


    private void sendEmail(String email, String resetpasswordlink) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("sengkurdkharsawkpoh@gmail.com", "Sengkur Dkhar Support");
        helper.setTo(email);

        String subject = "Here's the link to reset your password";
        String content = "<p>Hello,</p>" +
                "<p>You have requested to reset your password.</p>" +
                "<p>Click the link below to change your password:</p> " +
                "<p><a href=\"" + resetpasswordlink + "\">Change my password </a></p>" +
                "<p>Ignore this email if you have not made the request</p>";
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }


    @GetMapping("/getUserByPhone")
    public String getUserByPhone(Model model, @ModelAttribute("phone") String phone) {

        // phone = "1235134123";
        System.out.println("Phone controller" + phone);
        model.addAttribute("user", doa.getUserByPhone(phone));
//        return
        return "test_page";
    }
}


