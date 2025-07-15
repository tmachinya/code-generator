package com.pm.codegenfarm.generator;

import com.pm.codegenfarm.model.ColumnMeta;
import com.pm.codegenfarm.model.TableMeta;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DtoGenerator {

    public static void generateDTOs(List<TableMeta> tables, String basePackage, String baseDir) throws Exception {
        for (TableMeta table : tables) {
            generateRequestDTO(table, basePackage, baseDir + "/request");
            generateResponseDTO(table, basePackage, baseDir + "/response");
        }
    }

    private static void generateRequestDTO(TableMeta table, String basePackage, String dir) throws Exception {
        String className = toPascalCase(table.getName()) + "RequestDTO";
        File file = new File(dir, className + ".java");
        ensureDirExists(file.getParent());

        try (FileWriter writer = new FileWriter(file)) {
            writer.write("package " + basePackage + ".dto.request;\n\n");
            writer.write("import lombok.*;\n");
            writer.write("import java.math.*;\n");
            writer.write("import java.time.*;\n\n");
            writer.write("@Data\n@NoArgsConstructor\n@AllArgsConstructor\n@Builder\n");
            writer.write("public class " + className + " {\n\n");

            for (ColumnMeta col : table.getColumns()) {
                if (col.getName().equalsIgnoreCase("id")) continue;
                String javaType = col.isForeignKey() ? "Long" : mapToJavaType(col.getType());
                String fieldName = toCamelCase(col.getName());
                writer.write("    private " + javaType + " " + fieldName + ";\n");
            }

            writer.write("}\n");
        }
    }

    private static void generateResponseDTO(TableMeta table, String basePackage, String dir) throws Exception {
        String className = toPascalCase(table.getName()) + "ResponseDTO";
        File file = new File(dir, className + ".java");
        ensureDirExists(file.getParent());

        try (FileWriter writer = new FileWriter(file)) {
            writer.write("package " + basePackage + ".dto.response;\n\n");
            writer.write("import lombok.*;\n");
            writer.write("import java.math.*;\n");
            writer.write("import java.time.*;\n\n");
            writer.write("@Data\n@NoArgsConstructor\n@AllArgsConstructor\n@Builder\n");
            writer.write("public class " + className + " {\n\n");

            for (ColumnMeta col : table.getColumns()) {
                String javaType = col.isForeignKey() ? "Long" : mapToJavaType(col.getType());
                String camelName = toCamelCase(col.getName());
                String fieldName = col.isForeignKey() && !camelName.endsWith("Id") ? camelName + "Id" : camelName;
                writer.write("    private " + javaType + " " + fieldName + ";\n");
            }

            writer.write("}\n");
        }
    }

    private static void ensureDirExists(String dirPath) throws Exception {
        Files.createDirectories(Path.of(dirPath));
    }

    private static String toPascalCase(String name) {
        StringBuilder sb = new StringBuilder();
        for (String part : name.split("_")) {
            sb.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1));
        }
        return sb.toString();
    }

    private static String toCamelCase(String name) {
        StringBuilder sb = new StringBuilder();
        String[] parts = name.split("_");
        sb.append(parts[0].toLowerCase());
        for (int i = 1; i < parts.length; i++) {
            sb.append(Character.toUpperCase(parts[i].charAt(0))).append(parts[i].substring(1));
        }
        return sb.toString();
    }

    private static String mapToJavaType(String sqlType) {
        sqlType = sqlType.toLowerCase();
        if (sqlType.contains("int")) return "Integer";
        if (sqlType.contains("serial")) return "Long";
        if (sqlType.contains("numeric") || sqlType.contains("decimal")) return "BigDecimal";
        if (sqlType.contains("timestamp")) return "LocalDateTime";
        if (sqlType.contains("date")) return "LocalDate";
        if (sqlType.contains("boolean")) return "Boolean";
        return "String";
    }
}
