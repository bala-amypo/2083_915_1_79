package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="location")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double latitude;
    private double longitude;
}
