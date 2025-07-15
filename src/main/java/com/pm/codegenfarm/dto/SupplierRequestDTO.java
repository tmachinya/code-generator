package com.pm.codegenfarm.dto;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierRequestDTO {

    private String name;
    private String contact_person;
    private String phone;
    private String email;
    private String address;
}
