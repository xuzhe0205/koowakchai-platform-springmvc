package com.koowakchai.hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_deliveryman", schema = "dbKoowakchai", catalog = "")
public class TDeliverymanEntity {
    private int id;
    private long userId;
    private byte rating;
    private Integer orderCount;
    private String status;
    private Integer assignedOrderId;
    private String errandType;

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
    @Column(name = "user_id")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "rating")
    public byte getRating() {
        return rating;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }

    @Basic
    @Column(name = "order_count")
    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "assigned_order_id")
    public Integer getAssignedOrderId() {
        return assignedOrderId;
    }

    public void setAssignedOrderId(Integer assignedOrderId) {
        this.assignedOrderId = assignedOrderId;
    }

    @Basic
    @Column(name = "errand_type")
    public String getErrandType() {
        return errandType;
    }

    public void setErrandType(String errandType) {
        this.errandType = errandType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TDeliverymanEntity that = (TDeliverymanEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (rating != that.rating) return false;
        if (orderCount != null ? !orderCount.equals(that.orderCount) : that.orderCount != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (assignedOrderId != null ? !assignedOrderId.equals(that.assignedOrderId) : that.assignedOrderId != null)
            return false;
        if (errandType != null ? !errandType.equals(that.errandType) : that.errandType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) rating;
        result = 31 * result + (orderCount != null ? orderCount.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (assignedOrderId != null ? assignedOrderId.hashCode() : 0);
        result = 31 * result + (errandType != null ? errandType.hashCode() : 0);
        return result;
    }
}
