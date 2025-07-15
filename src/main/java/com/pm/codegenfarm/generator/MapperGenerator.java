package com.pm.codegenfarm.generator;

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

            // Prepare full output path
            Path filePath = Path.of(outputDir, mapperName + ".java");
            Files.createDirectories(filePath.getParent());

            try (FileWriter writer = new FileWriter(filePath.toFile())) {
                // Write package declaration dynamically
                writer.write("package " + basePackage + ".mapper;\n\n");

                writer.write("import org.mapstruct.*;\n");
                writer.write("import " + basePackage + ".entity." + className + ";\n");
                writer.write("import " + basePackage + ".dto." + className + "RequestDTO;\n");
                writer.write("import " + basePackage + ".dto." + className + "ResponseDTO;\n\n");

                writer.write("@Mapper(componentModel = \"spring\")\n");
                writer.write("public interface " + mapperName + " {\n\n");
                writer.write("    " + className + " toEntity(" + className + "RequestDTO dto);\n");
                writer.write("    " + className + "ResponseDTO toResponseDto(" + className + " entity);\n");
                writer.write("    void updateEntity(@MappingTarget " + className + " entity, " + className + "RequestDTO dto);\n");

                writer.write("}\n");
            }
        }
    }

    private static String toPascalCase(String name) {
        StringBuilder sb = new StringBuilder();
        for (String part : name.split("_")) {
            sb.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1));
        }
        return sb.toString();
    }
}
