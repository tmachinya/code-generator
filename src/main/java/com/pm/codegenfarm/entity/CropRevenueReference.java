package com.pm.codegenfarm.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "crop_revenue_reference")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CropRevenueReference extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crop_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_crop_revenue_reference_crop_id"))
    private Crop crop;

    @Column(name = "yield_per_hectare")
    private BigDecimal yieldPerHectare;

    @Column(name = "unit_price_estimate")
    private BigDecimal unitPriceEstimate;

    @Column(name = "revenue_unit")
    private String revenueUnit;

}
