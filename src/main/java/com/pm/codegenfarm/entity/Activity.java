package com.pm.codegenfarm.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "activity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Activity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cost_center_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_activity_cost_center_id"))
    private CostCenter costCenter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_activity_season_id"))
    private Season season;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crop_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_activity_crop_id"))
    private Crop crop;

    @Column(name = "description")
    private String description;

}
