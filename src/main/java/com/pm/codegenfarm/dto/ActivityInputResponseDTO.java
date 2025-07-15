package com.pm.codegenfarm.dto;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityInputResponseDTO {

    private Long id;
    private Integer activity_id;
    private Integer input_item_id;
    private BigDecimal quantity_used;
    private LocalDateTime created_on;
    private String created_by;
    private LocalDateTime updated_on;
    private String updated_by;
}
