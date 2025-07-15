package com.pm.codegenfarm.dto;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CropRevenueReferenceResponseDTO {

    private Long id;
    private Integer crop_id;
    private BigDecimal yield_per_hectare;
    private BigDecimal unit_price_estimate;
    private String revenue_unit;
    private LocalDateTime created_on;
    private String created_by;
    private LocalDateTime updated_on;
    private String updated_by;
}
