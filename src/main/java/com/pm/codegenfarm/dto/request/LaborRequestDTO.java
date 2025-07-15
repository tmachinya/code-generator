package com.pm.codegenfarm.dto.request;

import lombok.*;
import java.math.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LaborRequestDTO {

    private String fullName;
    private String nationalId;
    private String contactNumber;
    private String employmentType;
    private Long gradeId;
    private LocalDate dateJoined;
    private Boolean isActive;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime updatedOn;
    private String updatedBy;
}
