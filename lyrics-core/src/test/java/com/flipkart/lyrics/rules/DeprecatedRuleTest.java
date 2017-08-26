/*
 * Copyright 2017 Flipkart Internet, pvt ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.flipkart.lyrics.rules;

import com.flipkart.lyrics.java.config.Tune;
import com.flipkart.lyrics.model.FieldModel;
import com.flipkart.lyrics.java.specs.FieldSpec;
import com.flipkart.lyrics.test.annotation.TuneProvider;
import com.flipkart.lyrics.test.extensions.ConfigurationExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by shrey.garg on 06/06/17.
 */
@ExtendWith(ConfigurationExtension.class)
public class DeprecatedRuleTest {

    @Test
    public void testDeprecatedField(@TuneProvider Tune tune) {
        FieldSpec.Builder builder = FieldSpec.builder(Integer.class, "test");
        FieldModel model = mock(FieldModel.class);
        when(model.isDeprecated()).thenReturn(true);

        new DeprecatedRule(null, null).process(builder, model);

        FieldSpec fieldSpec = builder.build();
        assertEquals("test", fieldSpec.name);
        assertEquals(1, fieldSpec.annotations.size());
        assertEquals(Deprecated.class.getName(), fieldSpec.annotations.get(0).type.toString());
    }

    @Test
    public void testNotDeprecatedField(@TuneProvider Tune tune) {
        FieldSpec.Builder builder = FieldSpec.builder(Integer.class, "test");
        FieldModel model = mock(FieldModel.class);
        when(model.isDeprecated()).thenReturn(false);

        new DeprecatedRule(null, null).process(builder, model);

        FieldSpec fieldSpec = builder.build();
        assertEquals("test", fieldSpec.name);
        assertEquals(0, fieldSpec.annotations.size());
    }
}