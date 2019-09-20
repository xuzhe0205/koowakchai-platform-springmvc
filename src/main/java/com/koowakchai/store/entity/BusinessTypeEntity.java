package com.koowakchai.store.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "business_type", schema = "dbKoowakChai")
public class BusinessTypeEntity implements Serializable {
    private long id;
    private String businessName;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "business_name")
    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
}
