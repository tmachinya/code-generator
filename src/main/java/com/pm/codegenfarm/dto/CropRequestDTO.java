package com.pm.codegenfarm.dto;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CropRequestDTO {

    private String name;
    private BigDecimal default_yield_per_ha;
    private Integer duration_days;
}
