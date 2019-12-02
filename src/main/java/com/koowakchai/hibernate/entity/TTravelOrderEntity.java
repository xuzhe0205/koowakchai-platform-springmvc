package com.koowakchai.hibernate.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "t_travel_order", schema = "dbKoowakchai", catalog = "")
public class TTravelOrderEntity {
    private long id;
    private String travelType;
    private Long driverId;
    private long passengerId;
    private Timestamp pickupTime;
    private String pickupLocation;
    private String dropoffLocation;
    private double price;
    private byte numPassenger;
    private String status;
    private String flightNumber;
    private Integer airportInfoId;

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
    @Column(name = "travel_type")
    public String getTravelType() {
        return travelType;
    }

    public void setTravelType(String travelType) {
        this.travelType = travelType;
    }

    @Basic
    @Column(name = "driver_id")
    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    @Basic
    @Column(name = "passenger_id")
    public long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(long passengerId) {
        this.passengerId = passengerId;
    }

    @Basic
    @Column(name = "pickup_time")
    public Timestamp getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(Timestamp pickupTime) {
        this.pickupTime = pickupTime;
    }

    @Basic
    @Column(name = "pickup_location")
    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    @Basic
    @Column(name = "dropoff_location")
    public String getDropoffLocation() {
        return dropoffLocation;
    }

    public void setDropoffLocation(String dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
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
    @Column(name = "num_passenger")
    public byte getNumPassenger() {
        return numPassenger;
    }

    public void setNumPassenger(byte numPassenger) {
        this.numPassenger = numPassenger;
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
    @Column(name = "flight_number")
    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    @Basic
    @Column(name = "airportInfo_id")
    public Integer getAirportInfoId() {
        return airportInfoId;
    }

    public void setAirportInfoId(Integer airportInfoId) {
        this.airportInfoId = airportInfoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TTravelOrderEntity that = (TTravelOrderEntity) o;

        if (id != that.id) return false;
        if (passengerId != that.passengerId) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (numPassenger != that.numPassenger) return false;
        if (travelType != null ? !travelType.equals(that.travelType) : that.travelType != null) return false;
        if (driverId != null ? !driverId.equals(that.driverId) : that.driverId != null) return false;
        if (pickupTime != null ? !pickupTime.equals(that.pickupTime) : that.pickupTime != null) return false;
        if (pickupLocation != null ? !pickupLocation.equals(that.pickupLocation) : that.pickupLocation != null)
            return false;
        if (dropoffLocation != null ? !dropoffLocation.equals(that.dropoffLocation) : that.dropoffLocation != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (flightNumber != null ? !flightNumber.equals(that.flightNumber) : that.flightNumber != null) return false;
        if (airportInfoId != null ? !airportInfoId.equals(that.airportInfoId) : that.airportInfoId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (travelType != null ? travelType.hashCode() : 0);
        result = 31 * result + (driverId != null ? driverId.hashCode() : 0);
        result = 31 * result + (int) (passengerId ^ (passengerId >>> 32));
        result = 31 * result + (pickupTime != null ? pickupTime.hashCode() : 0);
        result = 31 * result + (pickupLocation != null ? pickupLocation.hashCode() : 0);
        result = 31 * result + (dropoffLocation != null ? dropoffLocation.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) numPassenger;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (flightNumber != null ? flightNumber.hashCode() : 0);
        result = 31 * result + (airportInfoId != null ? airportInfoId.hashCode() : 0);
        return result;
    }
}
