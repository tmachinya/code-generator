package com.pm.codegenfarm.dto;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LaborResponseDTO {

    private Long id;
    private String full_name;
    private String national_id;
    private String contact_number;
    private String employment_type;
    private Integer grade_id;
    private LocalDate date_joined;
    private Boolean is_active;
    private LocalDateTime created_on;
    private String created_by;
    private LocalDateTime updated_on;
    private String updated_by;
}
