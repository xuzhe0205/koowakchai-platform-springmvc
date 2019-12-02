package com.koowakchai.hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_logistics_company", schema = "dbKoowakchai", catalog = "")
public class TLogisticsCompanyEntity {
    private int id;
    private String companyName;
    private long staffPhone;

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
    @Column(name = "company_name")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "staff_phone")
    public long getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(long staffPhone) {
        this.staffPhone = staffPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TLogisticsCompanyEntity that = (TLogisticsCompanyEntity) o;

        if (id != that.id) return false;
        if (staffPhone != that.staffPhone) return false;
        if (companyName != null ? !companyName.equals(that.companyName) : that.companyName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (int) (staffPhone ^ (staffPhone >>> 32));
        return result;
    }
}
