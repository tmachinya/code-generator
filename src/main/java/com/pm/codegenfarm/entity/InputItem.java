package com.pm.codegenfarm.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.*;
import java.time.*;

@Entity
@Table(name = "input_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InputItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "unit")
    private String unit;

    @Column(name = "buffer_level")
    private BigDecimal bufferLevel;

}
