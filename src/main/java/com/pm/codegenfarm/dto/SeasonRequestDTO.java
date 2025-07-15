package com.pm.codegenfarm.dto;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeasonRequestDTO {

    private String name;
    private LocalDate start_date;
    private LocalDate end_date;
}
