package com.pm.codegenfarm.dto;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CropRevenueReferenceRequestDTO {

    private Integer crop_id;
    private BigDecimal yield_per_hectare;
    private BigDecimal unit_price_estimate;
    private String revenue_unit;
}
