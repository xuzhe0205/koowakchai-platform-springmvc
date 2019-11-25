package com.koowakchai.hibernate.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_logistics_order", schema = "dbKoowakchai", catalog = "")
public class TLogisticsOrderEntity {
    private long id;
    private String logisticsCompany;
    private String trackingNumber;
    private Long staffPhone;

    private Set<TTotalOrderEntity> tTotalOrderEntitySet = new HashSet<>();

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
    @Column(name = "logistics_company")
    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }

    @Basic
    @Column(name = "tracking_number")
    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    @Basic
    @Column(name = "staff_phone")
    public Long getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(Long staffPhone) {
        this.staffPhone = staffPhone;
    }


    @OneToMany(mappedBy = "tLogisticsOrderEntity",cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<TTotalOrderEntity> gettTotalOrderEntitySet() {
        return tTotalOrderEntitySet;
    }

    public void settTotalOrderEntitySet(Set<TTotalOrderEntity> tTotalOrderEntitySet) {
        this.tTotalOrderEntitySet = tTotalOrderEntitySet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TLogisticsOrderEntity that = (TLogisticsOrderEntity) o;

        if (id != that.id) return false;
        if (logisticsCompany != null ? !logisticsCompany.equals(that.logisticsCompany) : that.logisticsCompany != null)
            return false;
        if (trackingNumber != null ? !trackingNumber.equals(that.trackingNumber) : that.trackingNumber != null)
            return false;
        if (staffPhone != null ? !staffPhone.equals(that.staffPhone) : that.staffPhone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (logisticsCompany != null ? logisticsCompany.hashCode() : 0);
        result = 31 * result + (trackingNumber != null ? trackingNumber.hashCode() : 0);
        result = 31 * result + (staffPhone != null ? staffPhone.hashCode() : 0);
        return result;
    }
}
