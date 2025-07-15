package com.pm.codegenfarm.dto.response;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CropInputReferenceResponseDTO {

    private Long id;
    private Long cropId;
    private Long inputItemId;
    private BigDecimal quantityPerHectare;
    private BigDecimal unitCostEstimate;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime updatedOn;
    private String updatedBy;
}
