package com.pm.codegenfarm.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "labor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Labor extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "national_id")
    private String nationalId;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "employment_type")
    private String employmentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grade_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_labor_grade_id"))
    private LaborGrade laborGrade;

    @Column(name = "date_joined")
    private LocalDate dateJoined;

    @Column(name = "is_active")
    private Boolean isActive;

}
