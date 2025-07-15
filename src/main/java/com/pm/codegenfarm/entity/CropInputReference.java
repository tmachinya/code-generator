package com.pm.codegenfarm.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "crop_input_reference")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CropInputReference extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crop_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_crop_input_reference_crop_id"))
    private Crop crop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "input_item_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_crop_input_reference_input_item_id"))
    private InputItem inputItem;

    @Column(name = "quantity_per_hectare")
    private BigDecimal quantityPerHectare;

    @Column(name = "unit_cost_estimate")
    private BigDecimal unitCostEstimate;

}
