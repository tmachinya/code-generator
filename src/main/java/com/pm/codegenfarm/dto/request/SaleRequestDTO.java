package com.pm.codegenfarm.dto.request;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleRequestDTO {

    private Long harvestId;
    private String buyer;
    private BigDecimal quantitySold;
    private BigDecimal unitPrice;
    private BigDecimal totalAmount;
    private LocalDate saleDate;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime updatedOn;
    private String updatedBy;
}
