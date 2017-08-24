/*
 * Copyright 2016 Flipkart Internet, pvt ltd.
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

package com.flipkart.lyrics.processor.modifiers;

import com.flipkart.lyrics.config.Tune;
import com.flipkart.lyrics.specs.TypeSpec;
import com.flipkart.lyrics.model.MetaInfo;
import com.flipkart.lyrics.model.TypeModel;
import com.flipkart.lyrics.processor.Handler;
import com.flipkart.lyrics.sets.RuleSet;

/**
 * Created by shrey.garg on 29/11/16.
 */
public class ModifiersHandler extends Handler {

    public ModifiersHandler(Tune tune, MetaInfo metaInfo, RuleSet ruleSet) {
        super(tune, metaInfo, ruleSet);
    }

    @Override
    public void process(TypeSpec.Builder typeBuilder, TypeModel typeModel) {
        if (typeModel.getModifiers().length == 0 && tune.getDefaultClassModifier() != null) {
            typeBuilder.addModifiers(tune.getDefaultClassModifier());
        } else {
            typeBuilder.addModifiers(typeModel.getModifiers());
        }
    }
}
