package com.pm.codegenfarm.dto;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CropResponseDTO {

    private Long id;
    private String name;
    private BigDecimal default_yield_per_ha;
    private Integer duration_days;
    private LocalDateTime created_on;
    private String created_by;
    private LocalDateTime updated_on;
    private String updated_by;
}
