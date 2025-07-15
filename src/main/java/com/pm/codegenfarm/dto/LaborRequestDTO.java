package com.pm.codegenfarm.dto;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LaborRequestDTO {

    private String full_name;
    private String national_id;
    private String contact_number;
    private String employment_type;
    private Integer grade_id;
    private LocalDate date_joined;
    private Boolean is_active;
}
