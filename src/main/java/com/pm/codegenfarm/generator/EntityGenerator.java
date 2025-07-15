package com.pm.codegenfarm.generator;

import com.pm.codegenfarm.model.ColumnMeta;
import com.pm.codegenfarm.model.TableMeta;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class EntityGenerator {

    public static void generateEntities(List<TableMeta> tables, String basePackage, String outputDir) throws Exception {
        ensureDirExists(outputDir);

        for (TableMeta table : tables) {
            String className = toCamelCase(table.getName(), true);
            StringBuilder sb = new StringBuilder();

            // Dynamic package declaration
            sb.append("package ").append(basePackage).append(".entity;\n\n");

            sb.append("import jakarta.persistence.*;\n");
            sb.append("import lombok.*;\n");
            sb.append("import java.math.*;\n");
            sb.append("import java.time.*;\n\n");

            sb.append("@Entity\n");
            sb.append("@Table(name = \"").append(table.getName()).append("\")\n");
            sb.append("@Getter\n@Setter\n@NoArgsConstructor\n@AllArgsConstructor\n@Builder\n");
            sb.append("public class ").append(className).append(" extends BaseEntity {\n\n");

            sb.append("    @Id\n");
            sb.append("    @GeneratedValue(strategy = GenerationType.IDENTITY)\n");
            sb.append("    private Long id;\n\n");

            for (ColumnMeta column : table.getColumns()) {
                if (column.getName().equalsIgnoreCase("id")) continue;
                if (isAuditField(column.getName())) continue;

                String javaType = mapSQLTypeToJava(column.getType());

                if (column.isForeignKey()) {
                    sb.append("    @ManyToOne(fetch = FetchType.LAZY)\n");
                    sb.append("    @JoinColumn(name = \"").append(column.getName()).append("\", referencedColumnName = \"")
                            .append(column.getReferencedColumn()).append("\", foreignKey = @ForeignKey(name = \"fk_")
                            .append(table.getName()).append("_").append(column.getName()).append("\"))\n");
                    sb.append("    private ").append(toCamelCase(column.getReferencedTable(), true)).append(" ")
                            .append(toCamelCase(column.getReferencedTable(), false)).append(";\n\n");
                } else {
                    sb.append("    @Column(name = \"").append(column.getName()).append("\"")
                            .append(column.isNullable() ? "" : ", nullable = false").append(")\n");
                    sb.append("    private ").append(javaType).append(" ").append(toCamelCase(column.getName(), false)).append(";\n\n");
                }
            }

            sb.append("}\n");

            Path filePath = Path.of(outputDir, className + ".java");
            ensureDirExists(filePath.getParent().toString());

            try (FileWriter writer = new FileWriter(filePath.toFile())) {
                writer.write(sb.toString());
            }
        }
    }

    private static boolean isAuditField(String fieldName) {
        return fieldName.equalsIgnoreCase("created_on") ||
                fieldName.equalsIgnoreCase("updated_on") ||
                fieldName.equalsIgnoreCase("created_by") ||
                fieldName.equalsIgnoreCase("updated_by");
    }

    private static void ensureDirExists(String dirPath) throws Exception {
        Files.createDirectories(Path.of(dirPath));
    }

    private static String mapSQLTypeToJava(String sqlType) {
        sqlType = sqlType.toLowerCase();
        if (sqlType.startsWith("varchar") || sqlType.startsWith("text")) return "String";
        if (sqlType.startsWith("int")) return "Integer";
        if (sqlType.startsWith("bigint")) return "Long";
        if (sqlType.startsWith("serial")) return "Long";
        if (sqlType.startsWith("numeric") || sqlType.startsWith("decimal")) return "BigDecimal";
        if (sqlType.startsWith("boolean")) return "Boolean";
        if (sqlType.startsWith("date")) return "LocalDate";
        if (sqlType.startsWith("timestamp")) return "LocalDateTime";
        return "String";
    }

    private static String toCamelCase(String input, boolean capitalizeFirst) {
        String[] parts = input.split("_");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            if (i == 0 && !capitalizeFirst) {
                sb.append(part.toLowerCase());
            } else {
                sb.append(part.substring(0, 1).toUpperCase());
                sb.append(part.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }
}
