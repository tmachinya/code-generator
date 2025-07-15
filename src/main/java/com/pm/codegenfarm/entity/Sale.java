package com.pm.codegenfarm.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "sale")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sale extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "harvest_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_sale_harvest_id"))
    private Harvest harvest;

    @Column(name = "buyer")
    private String buyer;

    @Column(name = "quantity_sold")
    private BigDecimal quantitySold;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "sale_date")
    private LocalDate saleDate;

}
