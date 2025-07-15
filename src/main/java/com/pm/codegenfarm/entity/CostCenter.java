package com.pm.codegenfarm.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "cost_center")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CostCenter extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "size_hectares")
    private BigDecimal sizeHectares;

    @Column(name = "location")
    private String location;

    @Column(name = "soil_type")
    private String soilType;

}
