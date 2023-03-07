package com.dkhar.sengkur.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "m_dorbar")
public class Dorbar {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long dorbarcode;
        @Column
        private String kyrteng_dorbar;
        @Column
        private String short_name;

    }
