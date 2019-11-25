package com.koowakchai.hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_business_subtype", schema = "dbKoowakchai", catalog = "")
public class TBusinessSubtypeEntity {
    private int id;
    private String subBusinessName;
    private Integer businessTypeId;

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
    @Column(name = "sub_business_name")
    public String getSubBusinessName() {
        return subBusinessName;
    }

    public void setSubBusinessName(String subBusinessName) {
        this.subBusinessName = subBusinessName;
    }

    @Basic
    @Column(name = "business_type_id")
    public Integer getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(Integer businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TBusinessSubtypeEntity that = (TBusinessSubtypeEntity) o;

        if (id != that.id) return false;
        if (subBusinessName != null ? !subBusinessName.equals(that.subBusinessName) : that.subBusinessName != null)
            return false;
        if (businessTypeId != null ? !businessTypeId.equals(that.businessTypeId) : that.businessTypeId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (subBusinessName != null ? subBusinessName.hashCode() : 0);
        result = 31 * result + (businessTypeId != null ? businessTypeId.hashCode() : 0);
        return result;
    }
}
