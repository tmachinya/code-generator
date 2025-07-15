package com.pm.codegenfarm.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "harvest")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Harvest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cost_center_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_harvest_cost_center_id"))
    private CostCenter costCenter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crop_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_harvest_crop_id"))
    private Crop crop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_harvest_season_id"))
    private Season season;

    @Column(name = "harvest_date")
    private LocalDate harvestDate;

    @Column(name = "yield_qty")
    private BigDecimal yieldQty;

    @Column(name = "unit")
    private String unit;

    @Column(name = "notes")
    private String notes;

}
