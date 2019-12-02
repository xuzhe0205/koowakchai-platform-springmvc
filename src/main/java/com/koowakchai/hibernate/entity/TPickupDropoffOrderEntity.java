package com.koowakchai.hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_pickup_dropoff_order", schema = "dbKoowakchai", catalog = "")
public class TPickupDropoffOrderEntity {
    private int id;
    private String region;
    private String pickupAddress;
    private String dropoffAddress;
    private String itemsType;
    private double itemsWeight;
    private String deliveryTime;
    private double deliveryFee;
    private double tips;
    private String remarks;
    private String status;
    private long customerId;
    private Long deliverymanId;

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
    @Column(name = "region")
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Basic
    @Column(name = "pickup_address")
    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    @Basic
    @Column(name = "dropoff_address")
    public String getDropoffAddress() {
        return dropoffAddress;
    }

    public void setDropoffAddress(String dropoffAddress) {
        this.dropoffAddress = dropoffAddress;
    }

    @Basic
    @Column(name = "items_type")
    public String getItemsType() {
        return itemsType;
    }

    public void setItemsType(String itemsType) {
        this.itemsType = itemsType;
    }

    @Basic
    @Column(name = "items_weight")
    public double getItemsWeight() {
        return itemsWeight;
    }

    public void setItemsWeight(double itemsWeight) {
        this.itemsWeight = itemsWeight;
    }

    @Basic
    @Column(name = "delivery_time")
    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    @Basic
    @Column(name = "delivery_fee")
    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    @Basic
    @Column(name = "tips")
    public double getTips() {
        return tips;
    }

    public void setTips(double tips) {
        this.tips = tips;
    }

    @Basic
    @Column(name = "remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
    @Column(name = "customerId")
    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "deliverymanId")
    public Long getDeliverymanId() {
        return deliverymanId;
    }

    public void setDeliverymanId(Long deliverymanId) {
        this.deliverymanId = deliverymanId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TPickupDropoffOrderEntity that = (TPickupDropoffOrderEntity) o;

        if (id != that.id) return false;
        if (Double.compare(that.itemsWeight, itemsWeight) != 0) return false;
        if (Double.compare(that.deliveryFee, deliveryFee) != 0) return false;
        if (Double.compare(that.tips, tips) != 0) return false;
        if (customerId != that.customerId) return false;
        if (region != null ? !region.equals(that.region) : that.region != null) return false;
        if (pickupAddress != null ? !pickupAddress.equals(that.pickupAddress) : that.pickupAddress != null)
            return false;
        if (dropoffAddress != null ? !dropoffAddress.equals(that.dropoffAddress) : that.dropoffAddress != null)
            return false;
        if (itemsType != null ? !itemsType.equals(that.itemsType) : that.itemsType != null) return false;
        if (deliveryTime != null ? !deliveryTime.equals(that.deliveryTime) : that.deliveryTime != null) return false;
        if (remarks != null ? !remarks.equals(that.remarks) : that.remarks != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (deliverymanId != null ? !deliverymanId.equals(that.deliverymanId) : that.deliverymanId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (pickupAddress != null ? pickupAddress.hashCode() : 0);
        result = 31 * result + (dropoffAddress != null ? dropoffAddress.hashCode() : 0);
        result = 31 * result + (itemsType != null ? itemsType.hashCode() : 0);
        temp = Double.doubleToLongBits(itemsWeight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (deliveryTime != null ? deliveryTime.hashCode() : 0);
        temp = Double.doubleToLongBits(deliveryFee);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(tips);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (int) (customerId ^ (customerId >>> 32));
        result = 31 * result + (deliverymanId != null ? deliverymanId.hashCode() : 0);
        return result;
    }
}
