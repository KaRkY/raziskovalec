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
package org.raziskovalec.domain.raziskovanje;

import java.util.Set;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import org.raziskovalec.domain.naslov.Naslov;
import org.raziskovalec.domain.patent.Patent;
import org.raziskovalec.domain.projekt.Projekt;
import org.raziskovalec.domain.projekt.ProjektnaSkupina;

/**
 * @author Rene Svetina
 * 
 */
@Data
@EqualsAndHashCode(of = { "ime", "telefonskaStevilka" })
public class Organizacija
{
    @Setter(AccessLevel.PROTECTED)
    private Long                  id;
    private String                ime;
    private String                telefonskaStevilka;
    private String                email;
    private String                www;
    private Naslov                naslov;
    private Set<Patent>           lastniskiPatenti;
    private Set<ProjektnaSkupina> projektneSkupine;
    private Set<Projekt>          projekti;
    private Set<Raziskovalec>     raziskovalci;
}
