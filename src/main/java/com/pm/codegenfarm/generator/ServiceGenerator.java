package com.pm.codegenfarm.generator;

import com.pm.codegenfarm.model.TableMeta;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class ServiceGenerator {

    public static void generateServices(List<TableMeta> tables, String basePackage, String baseDir) throws Exception {
        for (TableMeta table : tables) {
            String name = toPascalCase(table.getName());
            generateServiceInterface(basePackage, name, baseDir + "/service");
            generateServiceImpl(basePackage, name, baseDir + "/serviceImpl");
        }
    }

    private static void generateServiceInterface(String basePackage, String name, String outputDir) throws Exception {
        File dir = new File(outputDir);
        if (!dir.exists()) dir.mkdirs();

        File file = new File(dir, name + "Service.java");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("package " + basePackage + ".service;\n\n");
            writer.write("import " + basePackage + ".dto." + name + "RequestDTO;\n");
            writer.write("import " + basePackage + ".dto." + name + "ResponseDTO;\n");
            writer.write("import java.util.List;\n\n");

            writer.write("public interface " + name + "Service {\n\n");
            writer.write("    " + name + "ResponseDTO create(" + name + "RequestDTO request);\n");
            writer.write("    " + name + "ResponseDTO update(Long id, " + name + "RequestDTO request);\n");
            writer.write("    void delete(Long id);\n");
            writer.write("    " + name + "ResponseDTO getById(Long id);\n");
            writer.write("    List<" + name + "ResponseDTO> getAll();\n");
            writer.write("}\n");
        }
    }

    private static void generateServiceImpl(String basePackage, String name, String outputDir) throws Exception {
        File dir = new File(outputDir);
        if (!dir.exists()) dir.mkdirs();

        File file = new File(dir, name + "ServiceImpl.java");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("package " + basePackage + ".serviceImpl;\n\n");
            writer.write("import " + basePackage + ".dto." + name + "RequestDTO;\n");
            writer.write("import " + basePackage + ".dto." + name + "ResponseDTO;\n");
            writer.write("import " + basePackage + ".entity." + name + ";\n");
            writer.write("import " + basePackage + ".mapper." + name + "Mapper;\n");
            writer.write("import " + basePackage + ".repository." + name + "Repository;\n");
            writer.write("import " + basePackage + ".service." + name + "Service;\n");
            writer.write("import lombok.RequiredArgsConstructor;\n");
            writer.write("import org.springframework.stereotype.Service;\n");

            writer.write("import java.util.List;\n");
            writer.write("import java.util.stream.Collectors;\n");
            writer.write("import java.util.NoSuchElementException;\n\n");

            writer.write("@Service\n@RequiredArgsConstructor\n");
            writer.write("public class " + name + "ServiceImpl implements " + name + "Service {\n\n");
            writer.write("    private final " + name + "Repository repository;\n");
            writer.write("    private final " + name + "Mapper mapper;\n\n");

            writer.write("    @Override\n");
            writer.write("    public " + name + "ResponseDTO create(" + name + "RequestDTO request) {\n");
            writer.write("        var entity = mapper.toEntity(request);\n");
            writer.write("        return mapper.toResponseDto(repository.save(entity));\n");
            writer.write("    }\n\n");

            writer.write("    @Override\n");
            writer.write("    public " + name + "ResponseDTO update(Long id, " + name + "RequestDTO request) {\n");
            writer.write("        var entity = repository.findById(id).orElseThrow(() -> new NoSuchElementException(\"Not found\"));\n");
            writer.write("        mapper.updateEntity(entity, request);\n");
            writer.write("        return mapper.toResponseDto(repository.save(entity));\n");
            writer.write("    }\n\n");

            writer.write("    @Override\n");
            writer.write("    public void delete(Long id) {\n");
            writer.write("        repository.deleteById(id);\n");
            writer.write("    }\n\n");

            writer.write("    @Override\n");
            writer.write("    public " + name + "ResponseDTO getById(Long id) {\n");
            writer.write("        return repository.findById(id)\n");
            writer.write("            .map(mapper::toResponseDto)\n");
            writer.write("            .orElseThrow(() -> new NoSuchElementException(\"Not found\"));\n");
            writer.write("    }\n\n");

            writer.write("    @Override\n");
            writer.write("    public List<" + name + "ResponseDTO> getAll() {\n");
            writer.write("        return repository.findAll()\n");
            writer.write("            .stream()\n");
            writer.write("            .map(mapper::toResponseDto)\n");
            writer.write("            .collect(Collectors.toList());\n");
            writer.write("    }\n");

            writer.write("}\n");
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
