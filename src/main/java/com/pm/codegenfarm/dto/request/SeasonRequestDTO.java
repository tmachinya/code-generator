package com.pm.codegenfarm.dto.request;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeasonRequestDTO {

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime updatedOn;
    private String updatedBy;
}
