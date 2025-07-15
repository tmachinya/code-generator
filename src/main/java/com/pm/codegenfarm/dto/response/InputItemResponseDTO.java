package com.pm.codegenfarm.dto.response;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InputItemResponseDTO {

    private Long id;
    private String name;
    private String type;
    private String unit;
    private BigDecimal bufferLevel;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime updatedOn;
    private String updatedBy;
}
