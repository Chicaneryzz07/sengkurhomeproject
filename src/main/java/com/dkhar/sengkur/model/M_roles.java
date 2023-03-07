package com.dkhar.sengkur.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "m_roles")
public class M_roles {
    @Id
    private long roleid;
    @Column
    private String rolename;

    public M_roles() {
    }

    public M_roles(long roleid){this.roleid=roleid;}


}
