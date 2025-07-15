package com.pm.codegenfarm.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "field_crop")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FieldCrop extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cost_center_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_field_crop_cost_center_id"))
    private CostCenter costCenter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crop_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_field_crop_crop_id"))
    private Crop crop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_field_crop_season_id"))
    private Season season;

    @Column(name = "hectares_allocated")
    private BigDecimal hectaresAllocated;

}
