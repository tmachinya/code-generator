package com.pm.codegenfarm.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "labor_grade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LaborGrade extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "wage_type")
    private String wageType;

    @Column(name = "daily_rate")
    private BigDecimal dailyRate;

    @Column(name = "monthly_salary")
    private BigDecimal monthlySalary;

}
