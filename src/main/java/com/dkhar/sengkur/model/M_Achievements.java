package com.dkhar.sengkur.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "m_achievements")
public class M_Achievements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long achievement_id;

    @Column
    private String achievement_name;


}
