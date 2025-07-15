package com.pm.codegenfarm.parser;

import com.pm.codegenfarm.model.ColumnMeta;
import com.pm.codegenfarm.model.TableMeta;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.create.table.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class SQLTableParser {

    public static List<TableMeta> parseSQL(Path filePath) throws Exception {
        List<TableMeta> tables = new ArrayList<>();

        String content = Files.readString(filePath);
        String[] statements = content.split(";");

        for (String stmtText : statements) {
            Statement stmt;
            try {
                stmt = CCJSqlParserUtil.parse(stmtText);
            } catch (Exception e) {
                continue; // skip invalid SQL like INSERTs
            }

            if (stmt instanceof CreateTable createTable) {
                TableMeta table = new TableMeta();
                table.setName(createTable.getTable().getName());

                List<String> primaryKeys = new ArrayList<>();
                Map<String, String[]> foreignKeys = new HashMap<>();

                if (createTable.getIndexes() != null) {
                    for (Index index : createTable.getIndexes()) {
                        if ("PRIMARY KEY".equalsIgnoreCase(index.getType())) {
                            primaryKeys.addAll(index.getColumnsNames());
                        } else if (index instanceof ForeignKeyIndex fk) {
                            List<String> colNames = fk.getColumnsNames();
                            List<String> refCols = fk.getReferencedColumnNames();
                            for (int i = 0; i < colNames.size(); i++) {
                                foreignKeys.put(colNames.get(i), new String[]{
                                        fk.getTable().getName(), refCols.get(i)
                                });
                            }
                        }
                    }
                }

                for (ColumnDefinition colDef : createTable.getColumnDefinitions()) {
                    String colName = colDef.getColumnName();
                    String colType = colDef.getColDataType().toString();
                    boolean isNullable = true;
                    boolean isPrimary = false;
                    boolean isForeign = false;
                    String refTable = null;
                    String refColumn = "id"; // default to 'id'

                    List<String> specs = colDef.getColumnSpecs() != null ? colDef.getColumnSpecs() : new ArrayList<>();

                    for (int i = 0; i < specs.size(); i++) {
                        String spec = specs.get(i);
                        if (spec.equalsIgnoreCase("NOT NULL")) {
                            isNullable = false;
                        }
                        if (spec.equalsIgnoreCase("PRIMARY") && i + 1 < specs.size() && specs.get(i + 1).equalsIgnoreCase("KEY")) {
                            isPrimary = true;
                        }
                        if (spec.equalsIgnoreCase("REFERENCES") && i + 1 < specs.size()) {
                            isForeign = true;
                            String[] parts = specs.get(i + 1).replace("(", " ").replace(")", "").split("\\s+");
                            refTable = parts[0].trim();
                            if (parts.length > 1) {
                                refColumn = parts[1].trim();
                            }
                        }
                    }
                    ColumnMeta col = new ColumnMeta();
                    col.setName(colName);
                    col.setType(colType);
                    col.setNullable(isNullable);
                    col.setPrimaryKey(isPrimary || primaryKeys.contains(colName));

                    if (isForeign || foreignKeys.containsKey(colName)) {
                        col.setForeignKey(true);
                        col.setReferencedTable(refTable != null ? refTable : foreignKeys.get(colName)[0]);
                        col.setReferencedColumn(refColumn);
                    }

                    table.getColumns().add(col);
                }

                tables.add(table);
            }
        }

        return tables;
    }
}
