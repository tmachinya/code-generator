package com.pm.codegenfarm.dto;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LaborAssignmentResponseDTO {

    private Long id;
    private Integer labor_id;
    private Integer activity_id;
    private Integer cost_center_id;
    private Integer season_id;
    private BigDecimal days_worked;
    private String task_description;
    private LocalDate date_assigned;
    private LocalDateTime created_on;
    private String created_by;
    private LocalDateTime updated_on;
    private String updated_by;
}
