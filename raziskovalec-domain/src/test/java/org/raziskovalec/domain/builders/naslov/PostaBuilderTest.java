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

import org.raziskovalec.domain.naslov.Posta;
import org.testng.annotations.Test;

/**
 * @author Rene Svetina
 *
 */
public class PostaBuilderTest
{
    @Test
    public void shouldBuildEmptyPosta()
    {
        final Posta posta = new PostaBuilder().build();
        assertNull(posta.getId(), "Id");
        assertNull(posta.getIme(), "Ime");
        assertNull(posta.getPostnaStevilka(), "Postna stevilka");
    }
    
    @Test
    public void shouldBuildWithIme()
    {
        final Posta posta = new PostaBuilder().ime("ime").build();
        assertNull(posta.getId(), "Id");
        assertEquals("ime", posta.getIme(), "Ime");
        assertNull(posta.getPostnaStevilka(), "Postna stevilka");
    }
    
    @Test
    public void shouldBuildWithPostnaStevilka()
    {
        final Posta posta = new PostaBuilder().postnaStevilka(2000).build();
        assertNull(posta.getId(), "Id");
        assertNull(posta.getIme(), "Ime");
        assertEquals(new Integer(2000), posta.getPostnaStevilka(), "Postna stevilka");
    }
    
    @Test
    public void shouldBuildWithEverything()
    {
        final Posta posta = new PostaBuilder().ime("ime").postnaStevilka(2000).build();
        assertNull(posta.getId(), "Id");
        assertEquals("ime", posta.getIme(), "Ime");
        assertEquals(new Integer(2000), posta.getPostnaStevilka(), "Postna stevilka");
    }
}
