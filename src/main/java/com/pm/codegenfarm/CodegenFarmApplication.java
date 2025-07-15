package com.pm.codegenfarm;

import com.pm.codegenfarm.generator.*;
import com.pm.codegenfarm.model.TableMeta;
import com.pm.codegenfarm.parser.SQLTableParser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CodegenFarmApplication {

    public static void main(String[] args) throws Exception {
        System.out.println("🚀 Code Generator Starting...");

        // ✅ Determine project root
        String projectRoot = System.getProperty("user.dir");

        // ✅ Parse arguments or use defaults
        String migrationFile = args.length > 0 ? args[0] : "src/main/resources/db/migration/V1__init.sql";
        String basePackage = detectBasePackage(); // Auto-detect base package
        String outputDir = "src/main/java/" + basePackage.replace('.', '/');

        // ✅ Parse SQL
        Path migrationPath = Path.of(projectRoot, migrationFile);
        List<TableMeta> tables = SQLTableParser.parseSQL(migrationPath);

        // ✅ Print parsed table info
        for (TableMeta table : tables) {
            System.out.println("Table: " + table.getName());
            for (var column : table.getColumns()) {
                System.out.printf("  - %s (%s), PK: %s, FK: %s%n",
                        column.getName(),
                        column.getType(),
                        column.isPrimaryKey(),
                        column.isForeignKey() ? column.getReferencedTable() + "." + column.getReferencedColumn() : "no"
                );
            }
        }

        // ✅ Generate all layers
        EntityGenerator.generateEntities(tables, basePackage, outputDir + "/entity");
        RepositoryGenerator.generateRepositories(tables, basePackage, outputDir + "/repository");
        DtoGenerator.generateDTOs(tables, basePackage, outputDir + "/dto");
        MapperGenerator.generateMappers(tables, basePackage, outputDir + "/mapper");
        ServiceGenerator.generateServices(tables, basePackage, outputDir);
        ControllerGenerator.generateControllers(tables, basePackage, outputDir + "/controller");

        System.out.println("✅ Code generation completed.");
    }

    private static String detectBasePackage() {
        Path javaSrcDir = Path.of("src/main/java");
        try (var stream = Files.walk(javaSrcDir)) {
            return stream
                    .filter(p -> p.toString().endsWith(".java"))
                    .map(p -> {
                        Path relativePath = javaSrcDir.relativize(p.getParent());
                        return relativePath.toString().replace(File.separator, ".");
                    })
                    .findFirst()
                    .orElse("com.example");
        } catch (IOException e) {
            System.err.println("⚠️ Failed to detect base package. Using fallback.");
            return "com.example";
        }
    }

}
