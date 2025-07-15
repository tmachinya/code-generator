package com.pm.codegenfarm.generator;

import com.pm.codegenfarm.model.TableMeta;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class RepositoryGenerator {

    public static void generateRepositories(List<TableMeta> tables, String basePackage, String outputDir) throws Exception {
        for (TableMeta table : tables) {
            String className = toPascalCase(table.getName());
            String fileName = className + "Repository.java";

            Path filePath = Path.of(outputDir, fileName);
            Files.createDirectories(filePath.getParent());

            try (FileWriter writer = new FileWriter(filePath.toFile())) {
                // Package declaration
                writer.write("package " + basePackage + ".repository;\n\n");

                // Imports
                writer.write("import " + basePackage + ".entity." + className + ";\n");
                writer.write("import org.springframework.data.jpa.repository.JpaRepository;\n");
                writer.write("import org.springframework.stereotype.Repository;\n\n");

                // Repository interface
                writer.write("@Repository\n");
                writer.write("public interface " + className + "Repository extends JpaRepository<" + className + ", Long> {\n");
                writer.write("}\n");
            }
        }
    }

    private static String toPascalCase(String snake) {
        StringBuilder result = new StringBuilder();
        for (String part : snake.split("_")) {
            result.append(part.substring(0, 1).toUpperCase()).append(part.substring(1));
        }
        return result.toString();
    }
}
