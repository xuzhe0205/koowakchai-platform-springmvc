package com.koowakchai.hibernate.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "t_total_order", schema = "dbKoowakchai", catalog = "")
public class TTotalOrderEntity {
    private long id;
    private String productName;
    private Integer productId;
    private Integer subtypeId;
    private String subtypeName;
    private double price;
    private Timestamp createDatetime;
    private Timestamp paidDatetime;
    private Timestamp shipDatetime;
    private int quantity;
    private String status;
    private Long paymentId;
    private double shipping;
    private Double pricePaid;
    private String remark;
    private String outTradeNumber;
    private String customerEmail;
    private String customerName;
    private String shippingAddr;
    private Long addrId;
    private String recipientName;
    private String comment;
    private String paymentMethod;
    private String trackingNumber;


    private TLogisticsOrderEntity tLogisticsOrderEntity;

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
    @Column(name = "product_name")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "product_id")
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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
    @Column(name = "subtype_name")
    public String getSubtypeName() {
        return subtypeName;
    }

    public void setSubtypeName(String subtypeName) {
        this.subtypeName = subtypeName;
    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "create_datetime")
    public Timestamp getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Timestamp createDatetime) {
        this.createDatetime = createDatetime;
    }

    @Basic
    @Column(name = "paid_datetime")
    public Timestamp getPaidDatetime() {
        return paidDatetime;
    }

    public void setPaidDatetime(Timestamp paidDatetime) {
        this.paidDatetime = paidDatetime;
    }

    @Basic
    @Column(name = "ship_datetime")
    public Timestamp getShipDatetime() {
        return shipDatetime;
    }

    public void setShipDatetime(Timestamp shipDatetime) {
        this.shipDatetime = shipDatetime;
    }

    @Basic
    @Column(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
    @Column(name = "payment_id")
    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    @Basic
    @Column(name = "shipping")
    public double getShipping() {
        return shipping;
    }

    public void setShipping(double shipping) {
        this.shipping = shipping;
    }

    @Basic
    @Column(name = "price_paid")
    public Double getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(Double pricePaid) {
        this.pricePaid = pricePaid;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "outTrade_number")
    public String getOutTradeNumber() {
        return outTradeNumber;
    }

    public void setOutTradeNumber(String outTradeNumber) {
        this.outTradeNumber = outTradeNumber;
    }

    @Basic
    @Column(name = "customer_email")
    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Basic
    @Column(name = "customer_name")
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Basic
    @Column(name = "shipping_addr")
    public String getShippingAddr() {
        return shippingAddr;
    }

    public void setShippingAddr(String shippingAddr) {
        this.shippingAddr = shippingAddr;
    }

    @Basic
    @Column(name = "addr_id")
    public Long getAddrId() {
        return addrId;
    }

    public void setAddrId(Long addrId) {
        this.addrId = addrId;
    }

    @Basic
    @Column(name = "recipient_name")
    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "payment_method")
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Basic
    @Column(name = "tracking_number")
    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="logistics_order_id")
    public TLogisticsOrderEntity gettLogisticsOrderEntity() {
        return tLogisticsOrderEntity;
    }

    public void settLogisticsOrderEntity(TLogisticsOrderEntity tLogisticsOrderEntity) {
        this.tLogisticsOrderEntity = tLogisticsOrderEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TTotalOrderEntity that = (TTotalOrderEntity) o;

        if (id != that.id) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (quantity != that.quantity) return false;
        if (Double.compare(that.shipping, shipping) != 0) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        if (subtypeId != null ? !subtypeId.equals(that.subtypeId) : that.subtypeId != null) return false;
        if (subtypeName != null ? !subtypeName.equals(that.subtypeName) : that.subtypeName != null) return false;
        if (createDatetime != null ? !createDatetime.equals(that.createDatetime) : that.createDatetime != null)
            return false;
        if (paidDatetime != null ? !paidDatetime.equals(that.paidDatetime) : that.paidDatetime != null) return false;
        if (shipDatetime != null ? !shipDatetime.equals(that.shipDatetime) : that.shipDatetime != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (paymentId != null ? !paymentId.equals(that.paymentId) : that.paymentId != null) return false;
        if (pricePaid != null ? !pricePaid.equals(that.pricePaid) : that.pricePaid != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (outTradeNumber != null ? !outTradeNumber.equals(that.outTradeNumber) : that.outTradeNumber != null)
            return false;
        if (customerEmail != null ? !customerEmail.equals(that.customerEmail) : that.customerEmail != null)
            return false;
        if (customerName != null ? !customerName.equals(that.customerName) : that.customerName != null) return false;
        if (shippingAddr != null ? !shippingAddr.equals(that.shippingAddr) : that.shippingAddr != null) return false;
        if (addrId != null ? !addrId.equals(that.addrId) : that.addrId != null) return false;
        if (recipientName != null ? !recipientName.equals(that.recipientName) : that.recipientName != null)
            return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (paymentMethod != null ? !paymentMethod.equals(that.paymentMethod) : that.paymentMethod != null)
            return false;
        if (trackingNumber != null ? !trackingNumber.equals(that.trackingNumber) : that.trackingNumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (subtypeId != null ? subtypeId.hashCode() : 0);
        result = 31 * result + (subtypeName != null ? subtypeName.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (createDatetime != null ? createDatetime.hashCode() : 0);
        result = 31 * result + (paidDatetime != null ? paidDatetime.hashCode() : 0);
        result = 31 * result + (shipDatetime != null ? shipDatetime.hashCode() : 0);
        result = 31 * result + quantity;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (paymentId != null ? paymentId.hashCode() : 0);
        temp = Double.doubleToLongBits(shipping);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (pricePaid != null ? pricePaid.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (outTradeNumber != null ? outTradeNumber.hashCode() : 0);
        result = 31 * result + (customerEmail != null ? customerEmail.hashCode() : 0);
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        result = 31 * result + (shippingAddr != null ? shippingAddr.hashCode() : 0);
        result = 31 * result + (addrId != null ? addrId.hashCode() : 0);
        result = 31 * result + (recipientName != null ? recipientName.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (paymentMethod != null ? paymentMethod.hashCode() : 0);
        result = 31 * result + (trackingNumber != null ? trackingNumber.hashCode() : 0);
        return result;
    }
}
