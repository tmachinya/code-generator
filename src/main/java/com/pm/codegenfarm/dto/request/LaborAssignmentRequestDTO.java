package com.pm.codegenfarm.dto.request;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LaborAssignmentRequestDTO {

    private Long laborId;
    private Integer activityId;
    private Long costCenterId;
    private Long seasonId;
    private BigDecimal daysWorked;
    private String taskDescription;
    private LocalDate dateAssigned;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime updatedOn;
    private String updatedBy;
}
