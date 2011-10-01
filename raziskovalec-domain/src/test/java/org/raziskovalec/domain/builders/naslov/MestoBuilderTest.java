/**
 * Copyright 2011 Rene Svetina
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.raziskovalec.domain.builders.naslov;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import org.raziskovalec.domain.naslov.Mesto;
import org.testng.annotations.Test;


/**
 * @author Rene Svetina
 * 
 */
public class MestoBuilderTest
{
    @Test
    public void shouldBuildEmptyMesto()
    {
        final Mesto mesto = new MestoBuilder().build();
        assertNull(mesto.getId(), "Id");
        assertNull(mesto.getIme(), "Ime");
    }
    
    @Test
    public void shouldBuildWithIme()
    {
        final Mesto mesto = new MestoBuilder().ime("ime").build();
        assertNull(mesto.getId(), "Id");
        assertEquals("ime", mesto.getIme(), "Ime");
    }
}
