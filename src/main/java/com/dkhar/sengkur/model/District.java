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

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@Entity
@Table(name = "m_district")
public class District implements Serializable {
    @Id
    @Column(name = "districtcode")
    private String districtcode;
    
    @Column(name = "districtname")
    private String districtname;

    @Column(name = "statecode")
    public String states;

    @Column(name = "shortname")
    private String shortname;


}
