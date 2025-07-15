package com.pm.codegenfarm.generator;

import com.pm.codegenfarm.model.ColumnMeta;
import com.pm.codegenfarm.model.TableMeta;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MapperGenerator {

    public static void generateMappers(List<TableMeta> tables, String basePackage, String outputDir) throws Exception {
        for (TableMeta table : tables) {
            String className = toPascalCase(table.getName());
            String mapperName = className + "Mapper";
            String requestDto = className + "RequestDTO";
            String responseDto = className + "ResponseDTO";

            Path filePath = Path.of(outputDir, mapperName + ".java");
            Files.createDirectories(filePath.getParent());

            try (FileWriter writer = new FileWriter(filePath.toFile())) {
                writer.write("package " + basePackage + ".mapper;\n\n");
                writer.write("import " + basePackage + ".dto.request." + requestDto + ";\n");
                writer.write("import " + basePackage + ".dto.response." + responseDto + ";\n");
                writer.write("import " + basePackage + ".entity." + className + ";\n");

                for (ColumnMeta col : table.getColumns()) {
                    if (col.isForeignKey()) {
                        String refClass = toPascalCase(col.getReferencedTable());
                        writer.write("import " + basePackage + ".entity." + refClass + ";\n");
                        writer.write("import " + basePackage + ".repository." + refClass + "Repository;\n");
                    }
                }

                writer.write("import lombok.RequiredArgsConstructor;\n");
                writer.write("import org.springframework.stereotype.Component;\n");
                writer.write("import java.util.NoSuchElementException;\n\n");

                writer.write("@Component\n");
                writer.write("@RequiredArgsConstructor\n");
                writer.write("public class " + mapperName + " {\n\n");

                for (ColumnMeta col : table.getColumns()) {
                    if (col.isForeignKey()) {
                        String refClass = toPascalCase(col.getReferencedTable());
                        writer.write("    private final " + refClass + "Repository " + toCamelCase(refClass) + "Repository;\n");
                    }
                }

                // toEntity
                writer.write("\n    public " + className + " toEntity(" + requestDto + " dto) {\n");
                writer.write("        " + className + " entity = " + className + ".builder()\n");

                for (ColumnMeta col : table.getColumns()) {
                    if (isAuditField(col.getName()) || col.getName().equalsIgnoreCase("id")) continue;

                    String field = toCamelCase(col.getName());

                    if (col.isForeignKey()) {
                        String refClass = toPascalCase(col.getReferencedTable());
                        writer.write("                ." + toCamelCase(refClass) + "(" + toCamelCase(refClass) + "Repository.findById(dto.get" + refClass + "Id())\n");
                        writer.write("                    .orElseThrow(() -> new NoSuchElementException(\"" + refClass + " not found\")))\n");
                    } else {
                        writer.write("                ." + field + "(dto.get" + toPascalCase(field) + "())\n");
                    }
                }

                writer.write("                .build();\n");
                writer.write("        entity.setCreatedBy(dto.getCreatedBy());\n");
                writer.write("        entity.setUpdatedBy(dto.getUpdatedBy());\n");
                writer.write("        return entity;\n");
                writer.write("    }\n");

                // toDto
                writer.write("\n    public " + responseDto + " toDto(" + className + " entity) {\n");
                writer.write("        return " + responseDto + ".builder()\n");
                writer.write("                .id(entity.getId())\n");

                for (ColumnMeta col : table.getColumns()) {
                    if (isAuditField(col.getName()) || col.getName().equalsIgnoreCase("id")) continue;

                    String field = toCamelCase(col.getName());

                    if (col.isForeignKey()) {
                        String refClass = toPascalCase(col.getReferencedTable());
                        writer.write("                ." + field + "(entity.get" + refClass + "().getId())\n");
                    } else {
                        writer.write("                ." + field + "(entity.get" + toPascalCase(field) + "())\n");
                    }
                }

                writer.write("                .createdOn(entity.getCreatedOn())\n");
                writer.write("                .createdBy(entity.getCreatedBy())\n");
                writer.write("                .updatedOn(entity.getUpdatedOn())\n");
                writer.write("                .updatedBy(entity.getUpdatedBy())\n");
                writer.write("                .build();\n");
                writer.write("    }\n");


                // updateEntity
                writer.write("\n    public void updateEntity(" + className + " entity, " + requestDto + " dto) {\n");
                for (ColumnMeta col : table.getColumns()) {
                    if (isAuditField(col.getName()) || col.getName().equalsIgnoreCase("id")) continue;

                    if (col.isForeignKey()) {
                        String field = toCamelCase(col.getName());
                        String refClass = toPascalCase(col.getReferencedTable());
                        writer.write("        entity.set" + refClass + "(" + toCamelCase(refClass) + "Repository.findById(dto.get" + toPascalCase(field) + "())\n");
                        writer.write("                .orElseThrow(() -> new NoSuchElementException(\"" + refClass + " not found\")));\n");
                    } else {
                        String field = toPascalCase(toCamelCase(col.getName()));
                        writer.write("        entity.set" + field + "(dto.get" + field + "());\n");
                    }
                }
                writer.write("        entity.setUpdatedBy(dto.getUpdatedBy());\n");
                writer.write("    }\n");

                writer.write("}\n");
            }
        }
    }

    private static boolean isAuditField(String name) {
        return name.equalsIgnoreCase("created_on") ||
                name.equalsIgnoreCase("updated_on") ||
                name.equalsIgnoreCase("created_by") ||
                name.equalsIgnoreCase("updated_by");
    }

    private static String toPascalCase(String name) {
        StringBuilder sb = new StringBuilder();
        for (String part : name.split("_")) {
            sb.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1));
        }
        return sb.toString();
    }

    private static String toCamelCase(String name) {
        String pascal = toPascalCase(name);
        return Character.toLowerCase(pascal.charAt(0)) + pascal.substring(1);
    }
}
