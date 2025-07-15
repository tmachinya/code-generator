package com.pm.codegenfarm.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "labor_assignment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LaborAssignment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "labor_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_labor_assignment_labor_id"))
    private Labor labor;

    @Column(name = "activity_id")
    private Integer activityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cost_center_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_labor_assignment_cost_center_id"))
    private CostCenter costCenter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_labor_assignment_season_id"))
    private Season season;

    @Column(name = "days_worked")
    private BigDecimal daysWorked;

    @Column(name = "task_description")
    private String taskDescription;

    @Column(name = "date_assigned")
    private LocalDate dateAssigned;

}
