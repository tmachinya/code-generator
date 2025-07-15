package com.pm.codegenfarm.dto.response;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FieldCropResponseDTO {

    private Long id;
    private Long costCenterId;
    private Long cropId;
    private Long seasonId;
    private BigDecimal hectaresAllocated;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime updatedOn;
    private String updatedBy;
}
