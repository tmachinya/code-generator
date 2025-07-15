package com.pm.codegenfarm.dto;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityInputRequestDTO {

    private Integer activity_id;
    private Integer input_item_id;
    private BigDecimal quantity_used;
}
