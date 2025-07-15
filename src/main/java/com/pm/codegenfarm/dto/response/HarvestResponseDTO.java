package com.pm.codegenfarm.dto.response;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HarvestResponseDTO {

    private Long id;
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
