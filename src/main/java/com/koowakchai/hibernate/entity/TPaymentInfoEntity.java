package com.koowakchai.hibernate.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "t_payment_info", schema = "dbKoowakchai", catalog = "")
public class TPaymentInfoEntity {
    private int id;
    private long userId;
    private String method;
    private String cardNum;
    private String zipcode;
    private String cvv;
    private String expDate;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @NotNull
    @Column(name = "user_id")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @NotNull
    @Column(name = "method")
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Basic
    @NotNull
    @Column(name = "card_num")
    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    @Basic
    @NotNull
    @Column(name = "zipcode")
    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Basic
    @NotNull
    @Column(name = "cvv")
    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Basic
    @NotNull
    @Column(name = "exp_date")
    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TPaymentInfoEntity that = (TPaymentInfoEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (method != null ? !method.equals(that.method) : that.method != null) return false;
        if (cardNum != null ? !cardNum.equals(that.cardNum) : that.cardNum != null) return false;
        if (zipcode != null ? !zipcode.equals(that.zipcode) : that.zipcode != null) return false;
        if (cvv != null ? !cvv.equals(that.cvv) : that.cvv != null) return false;
        if (expDate != null ? !expDate.equals(that.expDate) : that.expDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (method != null ? method.hashCode() : 0);
        result = 31 * result + (cardNum != null ? cardNum.hashCode() : 0);
        result = 31 * result + (zipcode != null ? zipcode.hashCode() : 0);
        result = 31 * result + (cvv != null ? cvv.hashCode() : 0);
        result = 31 * result + (expDate != null ? expDate.hashCode() : 0);
        return result;
    }
}
