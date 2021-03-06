package com.koowakchai.hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_liquor", schema = "dbKoowakchai", catalog = "")
public class TLiquorEntity {
    private int id;
    private Integer subtypeId;
    private String name;
    private String madeCountry;
    private String brand;
    private String alcVol;
    private String size;
    private String category;
    private String year;
    private Double price;
    private Integer stock;
    private Integer salesVol;
    private String productUrl;

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
    @Column(name = "subtype_id")
    public Integer getSubtypeId() {
        return subtypeId;
    }

    public void setSubtypeId(Integer subtypeId) {
        this.subtypeId = subtypeId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "made_country")
    public String getMadeCountry() {
        return madeCountry;
    }

    public void setMadeCountry(String madeCountry) {
        this.madeCountry = madeCountry;
    }

    @Basic
    @Column(name = "brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Basic
    @Column(name = "alc_vol")
    public String getAlcVol() {
        return alcVol;
    }

    public void setAlcVol(String alcVol) {
        this.alcVol = alcVol;
    }

    @Basic
    @Column(name = "size")
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Basic
    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column(name = "year")
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Basic
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "stock")
    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Basic
    @Column(name = "sales_vol")
    public Integer getSalesVol() {
        return salesVol;
    }

    public void setSalesVol(Integer salesVol) {
        this.salesVol = salesVol;
    }

    @Basic
    @Column(name = "product_url")
    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TLiquorEntity that = (TLiquorEntity) o;

        if (id != that.id) return false;
        if (subtypeId != null ? !subtypeId.equals(that.subtypeId) : that.subtypeId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (madeCountry != null ? !madeCountry.equals(that.madeCountry) : that.madeCountry != null) return false;
        if (brand != null ? !brand.equals(that.brand) : that.brand != null) return false;
        if (alcVol != null ? !alcVol.equals(that.alcVol) : that.alcVol != null) return false;
        if (size != null ? !size.equals(that.size) : that.size != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (stock != null ? !stock.equals(that.stock) : that.stock != null) return false;
        if (salesVol != null ? !salesVol.equals(that.salesVol) : that.salesVol != null) return false;
        if (productUrl != null ? !productUrl.equals(that.productUrl) : that.productUrl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (subtypeId != null ? subtypeId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (madeCountry != null ? madeCountry.hashCode() : 0);
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (alcVol != null ? alcVol.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (stock != null ? stock.hashCode() : 0);
        result = 31 * result + (salesVol != null ? salesVol.hashCode() : 0);
        result = 31 * result + (productUrl != null ? productUrl.hashCode() : 0);
        return result;
    }
}
