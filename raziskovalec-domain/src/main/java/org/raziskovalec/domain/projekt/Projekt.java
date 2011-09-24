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
package org.raziskovalec.domain.projekt;

import java.util.Set;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import org.joda.time.LocalDate;
import org.raziskovalec.domain.raziskovanje.Organizacija;
import org.raziskovalec.domain.raziskovanje.Raziskovalec;

/**
 * @author Rene Svetina
 * 
 */
@Data
@EqualsAndHashCode(of = { "ime" })
public class Projekt
{
    @Setter(AccessLevel.PROTECTED)
    private Long                  id;
    private String                ime;
    private LocalDate             datumZacetka;
    private LocalDate             datumZakljucka;
    private String                opis;
    private String                namen;
    private Tag                   tag;
    private Organizacija          vodilnaOrganizacija;
    private ProjektnaSkupina      vodilnaSkupina;
    private Set<Organizacija>     sudelujoceOrganizacije;
    private Set<ProjektnaSkupina> sudelujoceSkupine;
    private Raziskovalec          projektniVodja;
    private Set<Raziskovalec>     neodvisniRaziskovalci;
}
