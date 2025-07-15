package com.pm.codegenfarm.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "crop")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Crop extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "default_yield_per_ha")
    private BigDecimal defaultYieldPerHa;

    @Column(name = "duration_days")
    private Integer durationDays;

}
