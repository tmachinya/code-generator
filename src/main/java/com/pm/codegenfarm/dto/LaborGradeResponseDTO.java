package com.pm.codegenfarm.dto;

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
    private String wage_type;
    private BigDecimal daily_rate;
    private BigDecimal monthly_salary;
    private LocalDateTime created_on;
    private String created_by;
    private LocalDateTime updated_on;
    private String updated_by;
}
