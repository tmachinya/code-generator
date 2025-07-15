package com.pm.codegenfarm.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "activity_input")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityInput extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_activity_input_activity_id"))
    private Activity activity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "input_item_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_activity_input_input_item_id"))
    private InputItem inputItem;

    @Column(name = "quantity_used")
    private BigDecimal quantityUsed;

}
