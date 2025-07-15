package com.pm.codegenfarm.dto;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InputItemRequestDTO {

    private String name;
    private String type;
    private String unit;
    private BigDecimal buffer_level;
}
