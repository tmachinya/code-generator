package com.pm.codegenfarm.dto;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeasonResponseDTO {

    private Long id;
    private String name;
    private LocalDate start_date;
    private LocalDate end_date;
    private LocalDateTime created_on;
    private String created_by;
    private LocalDateTime updated_on;
    private String updated_by;
}
