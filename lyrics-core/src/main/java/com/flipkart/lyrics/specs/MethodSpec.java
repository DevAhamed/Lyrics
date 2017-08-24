package com.flipkart.lyrics.specs;

import com.flipkart.lyrics.Song;

import java.lang.reflect.Type;
import java.util.*;

/**
 * @author kushal.sharma on 09/08/17.
 */
public class MethodSpec {
    public final String name;
    public final CodeBlock defaultValue;
    public final TypeName returnType;
    public final Set<Modifier> modifiers = new HashSet<>();
    public final List<CodeBlock> codeBlocks = new ArrayList<>();
    public final List<CodeBlock> comments = new ArrayList<>();
    public final List<CodeBlock> statements = new ArrayList<>();
    public final List<AnnotationSpec> annotations = new ArrayList<>();
    public final List<ParameterSpec> parameters = new ArrayList<>();

    protected MethodSpec(Builder builder) {
        this.name = builder.name;
        this.modifiers.addAll(builder.modifiers);
        this.defaultValue = builder.defaultValue;
        this.returnType = builder.returnType;
        this.codeBlocks.addAll(builder.codeBlocks);
        this.comments.addAll(builder.comments);
        this.statements.addAll(builder.statements);
        this.annotations.addAll(builder.annotations);
        this.parameters.addAll(builder.parameters);
    }

    public static Builder methodBuilder(String name) {
        return Song.factory.createMethodBuilder(name);
    }

    public static Builder constructorBuilder() {
        return Song.factory.createConstructorBuilder();
    }

    public Object getMethodSpec() {
        return null;
    }

    public static abstract class Builder {
        private final String name;
        private CodeBlock defaultValue;
        private TypeName returnType;
        private final Set<Modifier> modifiers = new HashSet<>();
        private final List<CodeBlock> codeBlocks = new ArrayList<>();
        private final List<CodeBlock> comments = new ArrayList<>();
        private final List<CodeBlock> statements = new ArrayList<>();
        private final List<AnnotationSpec> annotations = new ArrayList<>();
        private final List<ParameterSpec> parameters = new ArrayList<>();

        protected Builder(String name) {
            this.name = name;
        }

        public MethodSpec.Builder addModifiers(Modifier... modifiers) {
            this.modifiers.addAll(Arrays.asList(modifiers));
            return this;
        }

        public MethodSpec.Builder returns(TypeName typeName) {
            this.returnType = typeName;
            return this;
        }

        public MethodSpec.Builder returns(Type type) {
            this.returnType = TypeName.get(type);
            return this;
        }

        public MethodSpec.Builder addStatement(String format, Object... args) {
            this.statements.add(CodeBlock.of(format, args));
            return this;
        }

        public MethodSpec.Builder addAnnotation(Class<?> clazz) {
            this.annotations.add(AnnotationSpec.builder(clazz).build());
            return this;
        }

        public MethodSpec.Builder addAnnotation(ClassName className) {
            this.annotations.add(AnnotationSpec.builder(className).build());
            return this;
        }

        public MethodSpec.Builder addCode(String format, Object... args) {
            this.codeBlocks.add(CodeBlock.of(format, args));
            return this;
        }

        public MethodSpec.Builder addComment(String format, Object... args) {
            this.comments.add(CodeBlock.of(format, args));
            return this;
        }

        public MethodSpec.Builder addParameter(ParameterSpec parameterSpec) {
            this.parameters.add(parameterSpec);
            return this;
        }

        public MethodSpec.Builder addParameter(TypeName typeName, String name, Modifier... modifiers) {
            this.parameters.add(ParameterSpec.builder(typeName, name, modifiers).build());
            return this;
        }

        public MethodSpec.Builder addParameter(Class<?> type, String name, Modifier... modifiers) {
            this.parameters.add(ParameterSpec.builder(type, name, modifiers).build());
            return this;
        }

        public MethodSpec.Builder defaultValue(String format, Object... args) {
            this.defaultValue = CodeBlock.of(format, args);
            return this;
        }

        public abstract MethodSpec build();
    }
}