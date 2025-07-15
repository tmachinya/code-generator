package com.pm.codegenfarm.dto.request;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HarvestRequestDTO {

    private Long costCenterId;
    private Long cropId;
    private Long seasonId;
    private LocalDate harvestDate;
    private BigDecimal yieldQty;
    private String unit;
    private String notes;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime updatedOn;
    private String updatedBy;
}
