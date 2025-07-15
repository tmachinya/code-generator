package com.pm.codegenfarm.dto;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FieldCropResponseDTO {

    private Long id;
    private Integer cost_center_id;
    private Integer crop_id;
    private Integer season_id;
    private BigDecimal hectares_allocated;
    private LocalDateTime created_on;
    private String created_by;
    private LocalDateTime updated_on;
    private String updated_by;
}
