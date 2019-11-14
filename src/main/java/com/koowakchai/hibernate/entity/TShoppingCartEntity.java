package com.koowakchai.hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_shopping_cart", schema = "dbKoowakchai", catalog = "")
public class TShoppingCartEntity {
    private int id;
    private Long userId;
    private Integer productSubtypeId;
    private Integer productId;
    private Integer quantity;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "product_subtype_id")
    public Integer getProductSubtypeId() {
        return productSubtypeId;
    }

    public void setProductSubtypeId(Integer productSubtypeId) {
        this.productSubtypeId = productSubtypeId;
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
    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TShoppingCartEntity that = (TShoppingCartEntity) o;

        if (id != that.id) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (productSubtypeId != null ? !productSubtypeId.equals(that.productSubtypeId) : that.productSubtypeId != null)
            return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (productSubtypeId != null ? productSubtypeId.hashCode() : 0);
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }
}
