package com.flipkart.lyrics;

import com.flipkart.lyrics.java.specs.Modifier;
import com.flipkart.lyrics.java.specs.ParameterSpec;
import com.flipkart.lyrics.java.specs.TypeName;

/**
 * @author kushal.sharma on 22/08/17.
 */
public class TestParameterSpec extends ParameterSpec {
    public TestParameterSpec(Builder builder) {
        super(builder);
    }

    public static final class Builder extends ParameterSpec.Builder {
        public Builder(TypeName typeName, String name, Modifier... modifiers) {
            super(typeName, name, modifiers);
        }

        public Builder(Class<?> clazz, String name, Modifier... modifiers) {
            super(clazz, name, modifiers);
        }

        @Override
        public ParameterSpec build() {
            return new TestParameterSpec(this);
        }
    }
}
