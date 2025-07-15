package com.pm.codegenfarm.dto;

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
    private String wage_type;
    private BigDecimal daily_rate;
    private BigDecimal monthly_salary;
}
