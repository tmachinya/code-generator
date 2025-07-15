package com.pm.codegenfarm.dto;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleResponseDTO {

    private Long id;
    private Integer harvest_id;
    private String buyer;
    private BigDecimal quantity_sold;
    private BigDecimal unit_price;
    private BigDecimal total_amount;
    private LocalDate sale_date;
    private LocalDateTime created_on;
    private String created_by;
    private LocalDateTime updated_on;
    private String updated_by;
}
