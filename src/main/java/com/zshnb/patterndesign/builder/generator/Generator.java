package com.zshnb.patterndesign.builder.generator;

import com.google.gson.Gson;
import com.squareup.javapoet.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.text.WordUtils;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Generator {
    public static Map<String, FieldTypeFormatAndValue> fieldInitializeValue = new HashMap<>();
    static {
        fieldInitializeValue.put("String", new FieldTypeFormatAndValue("$S", ""));
        fieldInitializeValue.put("int", new FieldTypeFormatAndValue("$L", 0));
        fieldInitializeValue.put("Integer", new FieldTypeFormatAndValue("$L", 0));
    }

    public static void main(String[] args) {
        Generator generator = new Generator();
        URL url = generator.getClass().getResource("/");
        List<File> jsonFiles = Arrays.stream(new File(url.getFile()).listFiles(pathname -> pathname.getName().endsWith(".json"))).collect(Collectors.toList());
        jsonFiles.forEach(it -> {
            System.out.println(it.getName());
            generator.generate(it);
        });
    }

    public void generate(File file) {

        Gson gson = new Gson();
        gson.fromJson()
        String entityName = file.getName().substring(0, file.getName().indexOf("."));
        String entityObjectLiteral = WordUtils.uncapitalize(entityName);

        TypeSpec.Builder entityTypeBuilder = TypeSpec.classBuilder(entityName)
            .addModifiers(Modifier.PUBLIC);
        MethodSpec entityConstructor = MethodSpec.constructorBuilder()
            .addModifiers(Modifier.PRIVATE).build();
        entityTypeBuilder.addMethod(entityConstructor);

        TypeSpec.Builder builderTypeBuilder = TypeSpec.classBuilder("Builder")
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .addField(entityClass, WordUtils.uncapitalize(entityClass.getSimpleName()));
        MethodSpec builderConstructor = MethodSpec.constructorBuilder()
            .addParameter(entityClass, entityObjectLiteral)
            .addStatement("this.$N = $N", entityObjectLiteral, entityObjectLiteral)
            .addModifiers(Modifier.PRIVATE).build();
        builderTypeBuilder.addMethod(builderConstructor);

        Arrays.stream(entityClass.getDeclaredFields()).forEach(it -> {
            FieldTypeFormatAndValue fieldTypeFormatAndValue = fieldInitializeValue.get(it.getType().getSimpleName());
            FieldSpec fieldSpec = FieldSpec.builder(it.getType(), it.getName(), Modifier.PRIVATE)
                .initializer(fieldTypeFormatAndValue.getFormat(), fieldTypeFormatAndValue.getValue()).build();
            entityTypeBuilder.addField(fieldSpec);
            MethodSpec setMethod = MethodSpec.methodBuilder(String.format("set%s", WordUtils.capitalize(it.getName())))
                .addModifiers(Modifier.PUBLIC)
                .returns(ClassName.get(entityClass.getPackageName(), "Builder"))
                .addStatement("$N.$N = $N", entityObjectLiteral, it.getName(), it.getName())
                .addStatement("return this").build();

            builderTypeBuilder.addMethod(setMethod);
            MethodSpec clearMethod = MethodSpec.methodBuilder(String.format("clear%s", WordUtils.capitalize(it.getName())))
                .addModifiers(Modifier.PUBLIC)
                .returns(ClassName.get(entityClass.getPackageName(), "Builder"))
                .addStatement(String.format("$N.$N = %s", fieldTypeFormatAndValue.getFormat()), entityObjectLiteral, it.getName(), fieldTypeFormatAndValue.getValue())
                .addStatement("return this").build();
            builderTypeBuilder.addMethod(clearMethod);

            MethodSpec getMethod = MethodSpec.methodBuilder(String.format("get%s", WordUtils.capitalize(it.getName())))
                .addModifiers(Modifier.PUBLIC)
                .returns(it.getType())
                .addStatement("return $N", it.getName()).build();
            entityTypeBuilder.addMethod(getMethod);
        });
        MethodSpec buildMethod = MethodSpec.methodBuilder("build")
            .addModifiers(Modifier.PUBLIC)
            .returns(entityClass)
            .addStatement("return $N", entityObjectLiteral).build();
        builderTypeBuilder.addMethod(buildMethod);

        entityTypeBuilder.addType(builderTypeBuilder.build());
        JavaFile javaFile = JavaFile.builder(entityClass.getPackageName(), entityTypeBuilder.build())
            .build();

        javaFile.writeTo(System.out);
    }
}
