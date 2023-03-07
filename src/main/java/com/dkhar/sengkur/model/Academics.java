package com.dkhar.sengkur.model;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "m_academics")
public class Academics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "dorbarcode")
    private Dorbar dorbarcode;

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

}
