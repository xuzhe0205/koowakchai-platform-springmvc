package com.koowakchai.hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_business", schema = "dbKoowakchai", catalog = "")
public class TBusinessEntity {
    private int id;
    private String businessName;
    private Integer subtypeId;
    private String subBusinessName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Basic
    @Column(name = "subtype_id")
    public Integer getSubtypeId() {
        return subtypeId;
    }

    public void setSubtypeId(Integer subtypeId) {
        this.subtypeId = subtypeId;
    }

    @Basic
    @Column(name = "sub_business_name")
    public String getSubBusinessName() {
        return subBusinessName;
    }

    public void setSubBusinessName(String subBusinessName) {
        this.subBusinessName = subBusinessName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TBusinessEntity that = (TBusinessEntity) o;

        if (id != that.id) return false;
        if (businessName != null ? !businessName.equals(that.businessName) : that.businessName != null) return false;
        if (subtypeId != null ? !subtypeId.equals(that.subtypeId) : that.subtypeId != null) return false;
        if (subBusinessName != null ? !subBusinessName.equals(that.subBusinessName) : that.subBusinessName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (businessName != null ? businessName.hashCode() : 0);
        result = 31 * result + (subtypeId != null ? subtypeId.hashCode() : 0);
        result = 31 * result + (subBusinessName != null ? subBusinessName.hashCode() : 0);
        return result;
    }
}
