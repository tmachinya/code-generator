package com.pm.codegenfarm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColumnMeta {
    private String name;
    private String type;
    private boolean isPrimaryKey;
    private boolean isNullable;
    private boolean isForeignKey;
    private String referencedTable;
    private String referencedColumn;
}
