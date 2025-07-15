package com.pm.codegenfarm.model;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class TableMeta {
    private String name;
    private List<ColumnMeta> columns = new ArrayList<>();
}
