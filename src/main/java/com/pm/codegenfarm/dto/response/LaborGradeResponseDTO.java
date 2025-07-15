package com.pm.codegenfarm.dto.response;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LaborGradeResponseDTO {

    private Long id;
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
