package com.koowakchai.hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_driver", schema = "dbKoowakchai", catalog = "")
public class TDriverEntity {
    private int id;
    private long userId;
    private String driverLicense;
    private String vehicleMake;
    private String vehicleModel;
    private int vehcleYear;
    private String vehicleClass;
    private double rating;
    private Integer tripCount;
    private String status;
    private Long assignedTripId;

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
    @Column(name = "driver_license")
    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    @Basic
    @Column(name = "vehicle_make")
    public String getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    @Basic
    @Column(name = "vehicle_model")
    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    @Basic
    @Column(name = "vehcle_year")
    public int getVehcleYear() {
        return vehcleYear;
    }

    public void setVehcleYear(int vehcleYear) {
        this.vehcleYear = vehcleYear;
    }

    @Basic
    @Column(name = "vehicle_class")
    public String getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    @Basic
    @Column(name = "rating")
    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Basic
    @Column(name = "trip_count")
    public Integer getTripCount() {
        return tripCount;
    }

    public void setTripCount(Integer tripCount) {
        this.tripCount = tripCount;
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
    @Column(name = "assigned_trip_id")
    public Long getAssignedTripId() {
        return assignedTripId;
    }

    public void setAssignedTripId(Long assignedTripId) {
        this.assignedTripId = assignedTripId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TDriverEntity that = (TDriverEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (vehcleYear != that.vehcleYear) return false;
        if (Double.compare(that.rating, rating) != 0) return false;
        if (driverLicense != null ? !driverLicense.equals(that.driverLicense) : that.driverLicense != null)
            return false;
        if (vehicleMake != null ? !vehicleMake.equals(that.vehicleMake) : that.vehicleMake != null) return false;
        if (vehicleModel != null ? !vehicleModel.equals(that.vehicleModel) : that.vehicleModel != null) return false;
        if (vehicleClass != null ? !vehicleClass.equals(that.vehicleClass) : that.vehicleClass != null) return false;
        if (tripCount != null ? !tripCount.equals(that.tripCount) : that.tripCount != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (assignedTripId != null ? !assignedTripId.equals(that.assignedTripId) : that.assignedTripId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (driverLicense != null ? driverLicense.hashCode() : 0);
        result = 31 * result + (vehicleMake != null ? vehicleMake.hashCode() : 0);
        result = 31 * result + (vehicleModel != null ? vehicleModel.hashCode() : 0);
        result = 31 * result + vehcleYear;
        result = 31 * result + (vehicleClass != null ? vehicleClass.hashCode() : 0);
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (tripCount != null ? tripCount.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (assignedTripId != null ? assignedTripId.hashCode() : 0);
        return result;
    }
}
