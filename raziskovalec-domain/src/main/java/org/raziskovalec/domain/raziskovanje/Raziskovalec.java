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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import org.raziskovalec.domain.naslov.Naslov;
import org.raziskovalec.domain.patent.Patent;
import org.raziskovalec.domain.projekt.Projekt;
import org.raziskovalec.domain.projekt.ProjektnaSkupina;
import org.raziskovalec.domain.znanja.Naziv;
import org.raziskovalec.domain.znanja.Znanje;

/**
 * @author Rene Svetina
 * 
 */
@Data
@EqualsAndHashCode(of = { "ime", "priimek", "telefonskaStevilka", "email" })
@Entity
public class Raziskovalec
{
    @Setter(AccessLevel.PROTECTED)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long                  id;
    @Column(nullable = false)
    private String                ime;
    @Column(nullable = false)
    private String                priimek;
    @Column(nullable = false)
    private String                telefonskaStevilka;
    @Column(nullable = false, unique = true)
    private String                email;
    private String                www;
    @ManyToOne(optional = false)
    @JoinColumn
    private Organizacija          organizacija;
    @ManyToOne(optional = false)
    @JoinColumn
    private Naslov                naslov;
    @ManyToMany
    private Set<Naziv>            nazivi;
    @ManyToMany(mappedBy = "neodvisniRaziskovalci")
    private Set<Projekt>          projekti;
    @OneToMany(mappedBy = "projektniVodja")
    private Set<Projekt>          vodiProjekte;
    @ManyToMany
    private Set<Znanje>           znanja;
    @ManyToMany(mappedBy = "raziskovalci")
    private Set<ProjektnaSkupina> projektneSkupine;
    @OneToMany(mappedBy = "vodja")
    private Set<ProjektnaSkupina> vodiSkupine;
    @OneToMany(mappedBy = "lastnikRaziskovalec")
    private Set<Patent>           lastniskiPatenti;
}
