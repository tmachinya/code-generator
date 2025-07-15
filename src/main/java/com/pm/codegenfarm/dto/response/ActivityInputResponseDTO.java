package com.pm.codegenfarm.dto.response;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityInputResponseDTO {

    private Long id;
    private Long activityId;
    private Long inputItemId;
    private BigDecimal quantityUsed;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime updatedOn;
    private String updatedBy;
}
