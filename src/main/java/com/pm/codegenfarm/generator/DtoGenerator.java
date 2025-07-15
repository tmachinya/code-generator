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
        ensureDirExists(baseDir);
        for (TableMeta table : tables) {
            generateRequestDTO(table, basePackage, baseDir);
            generateResponseDTO(table, basePackage, baseDir);
        }
    }

    private static void generateRequestDTO(TableMeta table, String basePackage, String baseDir) throws Exception {
        String className = toPascalCase(table.getName()) + "RequestDTO";
        File file = new File(baseDir, className + ".java");
        ensureDirExists(file.getParent());

        try (FileWriter writer = new FileWriter(file)) {
            writer.write("package " + basePackage + ".dto;\n\n");
            writer.write("import lombok.*;\n");
            writer.write("import java.math.*;\n");
            writer.write("import java.time.*;\n");
            writer.write("\n@Data\n@NoArgsConstructor\n@AllArgsConstructor\n@Builder\n");
            writer.write("public class " + className + " {\n\n");

            for (ColumnMeta col : table.getColumns()) {
                if (isAuditable(col.getName()) || col.getName().equals("id")) continue;
                writer.write("    private " + mapToJavaType(col.getType()) + " " + col.getName() + ";\n");
            }

            writer.write("}\n");
        }
    }

    private static void generateResponseDTO(TableMeta table, String basePackage, String baseDir) throws Exception {
        String className = toPascalCase(table.getName()) + "ResponseDTO";
        File file = new File(baseDir, className + ".java");
        ensureDirExists(file.getParent());

        try (FileWriter writer = new FileWriter(file)) {
            writer.write("package " + basePackage + ".dto;\n\n");
            writer.write("import lombok.*;\n");
            writer.write("import java.math.*;\n");
            writer.write("import java.time.*;\n");
            writer.write("\n@Data\n@NoArgsConstructor\n@AllArgsConstructor\n@Builder\n");
            writer.write("public class " + className + " {\n\n");

            for (ColumnMeta col : table.getColumns()) {
                writer.write("    private " + mapToJavaType(col.getType()) + " " + col.getName() + ";\n");
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

    private static boolean isAuditable(String name) {
        return name.equals("created_on") || name.equals("created_by") ||
                name.equals("updated_on") || name.equals("updated_by");
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
