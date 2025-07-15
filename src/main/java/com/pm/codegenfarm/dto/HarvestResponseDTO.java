package com.pm.codegenfarm.dto;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HarvestResponseDTO {

    private Long id;
    private Integer cost_center_id;
    private Integer crop_id;
    private Integer season_id;
    private LocalDate harvest_date;
    private BigDecimal yield_qty;
    private String unit;
    private String notes;
    private LocalDateTime created_on;
    private String created_by;
    private LocalDateTime updated_on;
    private String updated_by;
}
