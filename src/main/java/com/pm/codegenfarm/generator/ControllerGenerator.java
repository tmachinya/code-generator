package com.pm.codegenfarm.generator;

import com.pm.codegenfarm.model.TableMeta;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ControllerGenerator {

    public static void generateControllers(List<TableMeta> tables, String basePackage, String outputDir) throws Exception {
        ensureDirExists(outputDir);

        for (TableMeta table : tables) {
            String name = toPascalCase(table.getName());
            String varName = toCamelCase(name);

            File file = new File(outputDir, name + "Controller.java");
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("package " + basePackage + ".controller;\n\n");

                writer.write("import " + basePackage + ".dto." + name + "RequestDTO;\n");
                writer.write("import " + basePackage + ".dto." + name + "ResponseDTO;\n");
                writer.write("import " + basePackage + ".service." + name + "Service;\n");
                writer.write("import lombok.RequiredArgsConstructor;\n");
                writer.write("import org.springframework.http.ResponseEntity;\n");
                writer.write("import org.springframework.web.bind.annotation.*;\n");
                writer.write("import java.util.List;\n\n");

                writer.write("@RestController\n");
                writer.write("@RequestMapping(\"/api/" + table.getName() + "\")\n");
                writer.write("@RequiredArgsConstructor\n");
                writer.write("public class " + name + "Controller {\n\n");

                writer.write("    private final " + name + "Service " + varName + "Service;\n\n");

                // Create
                writer.write("    @PostMapping\n");
                writer.write("    public ResponseEntity<" + name + "ResponseDTO> create(@RequestBody " + name + "RequestDTO request) {\n");
                writer.write("        return ResponseEntity.ok(" + varName + "Service.create(request));\n");
                writer.write("    }\n\n");

                // Update
                writer.write("    @PutMapping(\"/{id}\")\n");
                writer.write("    public ResponseEntity<" + name + "ResponseDTO> update(@PathVariable Long id, @RequestBody " + name + "RequestDTO request) {\n");
                writer.write("        return ResponseEntity.ok(" + varName + "Service.update(id, request));\n");
                writer.write("    }\n\n");

                // Delete
                writer.write("    @DeleteMapping(\"/{id}\")\n");
                writer.write("    public ResponseEntity<Void> delete(@PathVariable Long id) {\n");
                writer.write("        " + varName + "Service.delete(id);\n");
                writer.write("        return ResponseEntity.noContent().build();\n");
                writer.write("    }\n\n");

                // Get by ID
                writer.write("    @GetMapping(\"/{id}\")\n");
                writer.write("    public ResponseEntity<" + name + "ResponseDTO> getById(@PathVariable Long id) {\n");
                writer.write("        return ResponseEntity.ok(" + varName + "Service.getById(id));\n");
                writer.write("    }\n\n");

                // Get all
                writer.write("    @GetMapping\n");
                writer.write("    public ResponseEntity<List<" + name + "ResponseDTO>> getAll() {\n");
                writer.write("        return ResponseEntity.ok(" + varName + "Service.getAll());\n");
                writer.write("    }\n");

                writer.write("}\n");
            }
        }
    }

    private static void ensureDirExists(String dirPath) throws Exception {
        Files.createDirectories(Path.of(dirPath));
    }

    private static String toPascalCase(String name) {
        StringBuilder result = new StringBuilder();
        for (String part : name.split("_")) {
            result.append(part.substring(0, 1).toUpperCase()).append(part.substring(1));
        }
        return result.toString();
    }

    private static String toCamelCase(String name) {
        String pascal = toPascalCase(name);
        return pascal.substring(0, 1).toLowerCase() + pascal.substring(1);
    }
}
