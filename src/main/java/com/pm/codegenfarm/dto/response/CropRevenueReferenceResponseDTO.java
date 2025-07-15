package com.pm.codegenfarm.dto.response;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CropRevenueReferenceResponseDTO {

    private Long id;
    private Long cropId;
    private BigDecimal yieldPerHectare;
    private BigDecimal unitPriceEstimate;
    private String revenueUnit;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime updatedOn;
    private String updatedBy;
}
