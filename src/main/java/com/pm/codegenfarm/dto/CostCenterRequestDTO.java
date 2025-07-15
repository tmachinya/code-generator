package com.pm.codegenfarm.dto;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CostCenterRequestDTO {

    private String name;
    private BigDecimal size_hectares;
    private String location;
    private String soil_type;
}
