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

import org.raziskovalec.domain.naslov.MestoBuilder;
import org.raziskovalec.domain.naslov.Naslov;
import org.raziskovalec.domain.naslov.NaslovBuilder;
import org.raziskovalec.domain.naslov.PostaBuilder;
import org.testng.annotations.Test;

/**
 * @author Rene Svetina
 *
 */
public class NaslovBuilderTest
{
    @Test
    public void shouldBuildEmptyNaslov()
    {
        final Naslov naslov = NaslovBuilder.naslov().build();
        assertNull(naslov.getId(), "Id");
        assertNull(naslov.getUlica(), "Ulica");
        assertNull(naslov.getHisnaStevilka(), "HisnaStevilka");
        assertNull(naslov.getMesto(), "Mesto");
        assertNull(naslov.getPosta(), "Posta");
    }

    @Test
    public void shouldBuildWithUlica()
    {
        final Naslov naslov = NaslovBuilder.naslov().ulica("Cankarjeva").build();
        assertNull(naslov.getId(), "Id");
        assertEquals("Cankarjeva", naslov.getUlica(), "Ulica");
        assertNull(naslov.getHisnaStevilka(), "HisnaStevilka");
        assertNull(naslov.getMesto(), "Mesto");
        assertNull(naslov.getPosta(), "Posta");
    }

    @Test
    public void shouldBuildWithHisnaStevilka()
    {
        final Naslov naslov = NaslovBuilder.naslov().hisnaStevilka("10a").build();
        assertNull(naslov.getId(), "Id");
        assertNull(naslov.getUlica(), "Ulica");
        assertEquals("10a", naslov.getHisnaStevilka(), "HisnaStevilka");
        assertNull(naslov.getMesto(), "Mesto");
        assertNull(naslov.getPosta(), "Posta");
    }

    @Test
    public void shouldBuildWithMesto()
    {
        final Naslov naslov = NaslovBuilder.naslov().mesto(MestoBuilder.mesto().ime("Maribor").build()).build();
        assertNull(naslov.getId(), "Id");
        assertNull(naslov.getUlica(), "Ulica");
        assertNull(naslov.getHisnaStevilka(), "HisnaStevilka");
        assertEquals(MestoBuilder.mesto().ime("Maribor").build(), naslov.getMesto(), "Mesto");
        assertNull(naslov.getPosta(), "Posta");
    }

    @Test
    public void shouldBuildPosta()
    {
        final Naslov naslov = NaslovBuilder.naslov()
                .posta(PostaBuilder.posta().ime("Maribor").postnaStevilka(2000).build())
                .build();
        assertNull(naslov.getId(), "Id");
        assertNull(naslov.getUlica(), "Ulica");
        assertNull(naslov.getHisnaStevilka(), "HisnaStevilka");
        assertNull(naslov.getMesto(), "Mesto");
        assertEquals(PostaBuilder.posta().ime("Maribor").postnaStevilka(2000).build(), naslov.getPosta(), "Posta");
    }

    @Test
    public void shouldBuildWithEverything()
    {
        final Naslov naslov = NaslovBuilder.naslov().ulica("Cankarjeva")
                .hisnaStevilka("10a")
                .mesto(MestoBuilder.mesto().ime("Maribor").build())
                .posta(PostaBuilder.posta().ime("Maribor").postnaStevilka(2000).build())
                .build();
        assertNull(naslov.getId(), "Id");
        assertEquals("Cankarjeva", naslov.getUlica(), "Ulica");
        assertEquals("10a", naslov.getHisnaStevilka(), "HisnaStevilka");
        assertEquals(MestoBuilder.mesto().ime("Maribor").build(), naslov.getMesto(), "Mesto");
        assertEquals(PostaBuilder.posta().ime("Maribor").postnaStevilka(2000).build(), naslov.getPosta(), "Posta");
    }

    @Test
    public void shouldBuildWithEverythingPostaBuilderMestoBuilder()
    {
        final Naslov naslov = NaslovBuilder.naslov().ulica("Cankarjeva")
                .hisnaStevilka("10a")
                .mesto(MestoBuilder.mesto().ime("Maribor"))
                .posta(PostaBuilder.posta().ime("Maribor").postnaStevilka(2000))
                .build();
        assertNull(naslov.getId(), "Id");
        assertEquals("Cankarjeva", naslov.getUlica(), "Ulica");
        assertEquals("10a", naslov.getHisnaStevilka(), "HisnaStevilka");
        assertEquals(MestoBuilder.mesto().ime("Maribor").build(), naslov.getMesto(), "Mesto");
        assertEquals(PostaBuilder.posta().ime("Maribor").postnaStevilka(2000).build(), naslov.getPosta(), "Posta");
    }
}
