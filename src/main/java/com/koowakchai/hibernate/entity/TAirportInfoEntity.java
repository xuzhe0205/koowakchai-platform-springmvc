package com.koowakchai.hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_airport_info", schema = "dbKoowakchai", catalog = "")
public class TAirportInfoEntity {
    private int id;
    private String city;
    private String airport;
    private String terminal;
    private String airline;

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
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "airport")
    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    @Basic
    @Column(name = "terminal")
    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    @Basic
    @Column(name = "airline")
    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TAirportInfoEntity that = (TAirportInfoEntity) o;

        if (id != that.id) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (airport != null ? !airport.equals(that.airport) : that.airport != null) return false;
        if (terminal != null ? !terminal.equals(that.terminal) : that.terminal != null) return false;
        if (airline != null ? !airline.equals(that.airline) : that.airline != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (airport != null ? airport.hashCode() : 0);
        result = 31 * result + (terminal != null ? terminal.hashCode() : 0);
        result = 31 * result + (airline != null ? airline.hashCode() : 0);
        return result;
    }
}
