package com.zshnb.patterndesign.builder.generator;

import com.google.gson.Gson;
import com.squareup.javapoet.*;
import com.zshnb.patterndesign.builder.User;
import org.apache.commons.io.FileUtils;
import org.apache.commons.text.WordUtils;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Generator {
    private static Map<String, FieldTypeFormatAndValue> fieldNameWithInitializeValue = new HashMap<>();
    private static Map<String, TypeName> fieldTypeNameWithTypeName = new HashMap<>();
    static {
        fieldNameWithInitializeValue.put("String", new FieldTypeFormatAndValue("$S", ""));
        fieldNameWithInitializeValue.put("int", new FieldTypeFormatAndValue("$L", 0));
        fieldNameWithInitializeValue.put("Integer", new FieldTypeFormatAndValue("$L", 0));

        fieldTypeNameWithTypeName.put("int", TypeName.INT);
        fieldTypeNameWithTypeName.put("Integer", TypeName.INT);
        fieldTypeNameWithTypeName.put("String", TypeName.get(String.class));
    }

    public void generate() {
        URL url = getClass().getResource("/");
        List<File> jsonFiles = Arrays.stream(new File(url.getFile()).listFiles(pathname -> pathname.getName().endsWith(".json"))).collect(Collectors.toList());
        jsonFiles.forEach(it -> {
            try {
                generate(FileUtils.readFileToString(it, StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void generate(String json) throws IOException {
        Gson gson = new Gson();
        Entity entity = gson.fromJson(json, Entity.class);
        String entityClassName = WordUtils.capitalize(entity.getName());
        String entityObjectLiteral = WordUtils.uncapitalize(entityClassName);
        ClassName className = ClassName.get(entity.getPackageName(), entityClassName);

        TypeSpec.Builder entityTypeBuilder = TypeSpec.classBuilder(entityClassName)
            .addModifiers(Modifier.PUBLIC);
        MethodSpec entityConstructor = MethodSpec.constructorBuilder()
            .addModifiers(Modifier.PRIVATE).build();
        entityTypeBuilder.addMethod(entityConstructor);

        TypeSpec.Builder builderTypeBuilder = TypeSpec.classBuilder("Builder")
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .addField(className, entityObjectLiteral, Modifier.PRIVATE);
        MethodSpec builderConstructor = MethodSpec.constructorBuilder()
            .addParameter(className, entityObjectLiteral)
            .addStatement("this.$N = $N", entityObjectLiteral, entityObjectLiteral)
            .addModifiers(Modifier.PRIVATE).build();
        builderTypeBuilder.addMethod(builderConstructor);

        entity.getFields().forEach(it -> {
            FieldTypeFormatAndValue fieldTypeFormatAndValue = fieldNameWithInitializeValue.get(it.getType());
            FieldSpec fieldSpec = FieldSpec.builder(fieldTypeNameWithTypeName.get(it.getType()), it.getName(), Modifier.PRIVATE)
                .initializer(fieldTypeFormatAndValue.getFormat(), fieldTypeFormatAndValue.getValue()).build();
            entityTypeBuilder.addField(fieldSpec);
            MethodSpec setMethod = MethodSpec.methodBuilder(String.format("set%s", WordUtils.capitalize(it.getName())))
                .addModifiers(Modifier.PUBLIC)
                .addParameter(fieldTypeNameWithTypeName.get(it.getType()), it.getName())
                .returns(ClassName.get("", "Builder"))
                .addStatement("$N.$N = $N", entityObjectLiteral, it.getName(), it.getName())
                .addStatement("return this").build();

            builderTypeBuilder.addMethod(setMethod);
            MethodSpec clearMethod = MethodSpec.methodBuilder(String.format("clear%s", WordUtils.capitalize(it.getName())))
                .addModifiers(Modifier.PUBLIC)
                .returns(ClassName.get("", "Builder"))
                .addStatement(String.format("$N.$N = %s", fieldTypeFormatAndValue.getFormat()), entityObjectLiteral, it.getName(), fieldTypeFormatAndValue.getValue())
                .addStatement("return this").build();
            builderTypeBuilder.addMethod(clearMethod);

            MethodSpec getMethod = MethodSpec.methodBuilder(String.format("get%s", WordUtils.capitalize(it.getName())))
                .addModifiers(Modifier.PUBLIC)
                .returns(fieldTypeNameWithTypeName.get(it.getType()))
                .addStatement("return $N", it.getName()).build();
            entityTypeBuilder.addMethod(getMethod);
        });
        MethodSpec buildMethod = MethodSpec.methodBuilder("build")
            .addModifiers(Modifier.PUBLIC)
            .returns(ClassName.get("", entityClassName))
            .addStatement("return $N", entityObjectLiteral).build();
        builderTypeBuilder.addMethod(buildMethod);

        MethodSpec newBuilderMethod = MethodSpec.methodBuilder("newBuilder")
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .returns(ClassName.get("", "Builder"))
            .addStatement("return new $N(new $N())", "Builder", entityClassName).build();
        entityTypeBuilder.addMethod(newBuilderMethod);

        MethodSpec toBuilderMethod = MethodSpec.methodBuilder("toBuilder")
            .addModifiers(Modifier.PUBLIC)
            .returns(ClassName.get("", "Builder"))
            .addStatement("return new $N(this)", "Builder").build();
        entityTypeBuilder.addMethod(toBuilderMethod);

        entityTypeBuilder.addType(builderTypeBuilder.build());
        JavaFile javaFile = JavaFile.builder(entity.getPackageName(), entityTypeBuilder.build())
            .build();

        javaFile.writeToFile(new File(String.format("src/main/java")));
    }
}
