package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Location {
    @Id @GeneratedValue
    private Long id;
    private Double latitude;
    private Double longitude;

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
}
