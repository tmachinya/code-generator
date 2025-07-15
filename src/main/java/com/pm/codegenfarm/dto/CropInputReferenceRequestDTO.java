package com.pm.codegenfarm.dto;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CropInputReferenceRequestDTO {

    private Integer crop_id;
    private Integer input_item_id;
    private BigDecimal quantity_per_hectare;
    private BigDecimal unit_cost_estimate;
}
