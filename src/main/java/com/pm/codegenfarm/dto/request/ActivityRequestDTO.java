package com.pm.codegenfarm.dto.request;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityRequestDTO {

    private String type;
    private LocalDate date;
    private Long costCenterId;
    private Long seasonId;
    private Long cropId;
    private String description;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime updatedOn;
    private String updatedBy;
}
