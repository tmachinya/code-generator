package com.pm.codegenfarm.dto;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FieldCropRequestDTO {

    private Integer cost_center_id;
    private Integer crop_id;
    private Integer season_id;
    private BigDecimal hectares_allocated;
}
