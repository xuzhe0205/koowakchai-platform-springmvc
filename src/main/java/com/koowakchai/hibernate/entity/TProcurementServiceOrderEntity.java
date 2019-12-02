package com.koowakchai.hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_procurement_service_order", schema = "dbKoowakchai", catalog = "")
public class TProcurementServiceOrderEntity {
    private int id;
    private String region;
    private String deliveryAddress;
    private String purchaseAddress;
    private String items;
    private String deliveryTime;
    private double deliveryFee;
    private double tips;
    private String status;
    private Double itemsPrice;
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
    @Column(name = "delivery_address")
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @Basic
    @Column(name = "purchase_address")
    public String getPurchaseAddress() {
        return purchaseAddress;
    }

    public void setPurchaseAddress(String purchaseAddress) {
        this.purchaseAddress = purchaseAddress;
    }

    @Basic
    @Column(name = "items")
    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
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
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "itemsPrice")
    public Double getItemsPrice() {
        return itemsPrice;
    }

    public void setItemsPrice(Double itemsPrice) {
        this.itemsPrice = itemsPrice;
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

        TProcurementServiceOrderEntity that = (TProcurementServiceOrderEntity) o;

        if (id != that.id) return false;
        if (Double.compare(that.deliveryFee, deliveryFee) != 0) return false;
        if (Double.compare(that.tips, tips) != 0) return false;
        if (customerId != that.customerId) return false;
        if (region != null ? !region.equals(that.region) : that.region != null) return false;
        if (deliveryAddress != null ? !deliveryAddress.equals(that.deliveryAddress) : that.deliveryAddress != null)
            return false;
        if (purchaseAddress != null ? !purchaseAddress.equals(that.purchaseAddress) : that.purchaseAddress != null)
            return false;
        if (items != null ? !items.equals(that.items) : that.items != null) return false;
        if (deliveryTime != null ? !deliveryTime.equals(that.deliveryTime) : that.deliveryTime != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (itemsPrice != null ? !itemsPrice.equals(that.itemsPrice) : that.itemsPrice != null) return false;
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
        result = 31 * result + (deliveryAddress != null ? deliveryAddress.hashCode() : 0);
        result = 31 * result + (purchaseAddress != null ? purchaseAddress.hashCode() : 0);
        result = 31 * result + (items != null ? items.hashCode() : 0);
        result = 31 * result + (deliveryTime != null ? deliveryTime.hashCode() : 0);
        temp = Double.doubleToLongBits(deliveryFee);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(tips);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (itemsPrice != null ? itemsPrice.hashCode() : 0);
        result = 31 * result + (int) (customerId ^ (customerId >>> 32));
        result = 31 * result + (deliverymanId != null ? deliverymanId.hashCode() : 0);
        return result;
    }
}
