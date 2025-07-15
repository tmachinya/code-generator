package com.pm.codegenfarm.dto.request;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LaborGradeRequestDTO {

    private String name;
    private String description;
    private String wageType;
    private BigDecimal dailyRate;
    private BigDecimal monthlySalary;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime updatedOn;
    private String updatedBy;
}
