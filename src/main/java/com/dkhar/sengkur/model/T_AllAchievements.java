package com.dkhar.sengkur.model;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "t_AllAchievements")
public class T_AllAchievements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long allachievement_id;

    //@Column
    //private String snem;
//    @Column
////    @DateTimeFormat(pattern = "dd-MM-yyyy")
//    private Date snem;
    @Column
    private String kyrteng;

    @Column
    private String field;

    @Column
    private String details;

    @Column
    private String dorbar;

      //    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column
    private LocalDate snem;

    @Column
    private String venue;

    @ManyToOne
    @JoinColumn(name = "achievement_id")
    private M_Achievements achievement_id;
}
