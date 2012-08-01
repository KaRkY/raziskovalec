/**
 * Copyright 2012 Rene Svetina
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.raziskovalec.domain.value;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.raziskovalec.Name;
import org.testng.annotations.Test;

/**
 * Name tests.
 * 
 * @author Rene Svetina
 */
public class NameShould {
  /**
   * Equal with same name.
   */
  @Test
  public void equalWithSameString() {
    String name = "Rene";
    Name name1 = Name.valueOf(name);
    Name name2 = Name.valueOf("Rene");

    assertThat(name1, is(equalTo(name2)));
  }

  /**
   * Fail on empty name.
   */
  @Test(expectedExceptions = { IllegalArgumentException.class })
  public void failOnEmptyName() {
    Name.valueOf("");
  }

  /**
   * Fail on N/A string.
   */
  @Test(expectedExceptions = { IllegalArgumentException.class })
  public void failOnNAName() {
    Name.valueOf("N/A");
  }

  /**
   * Return valid name.
   */
  @Test
  protected void returnName() {
    Name name = Name.valueOf("Rene");

    assertThat(name, is(notNullValue()));
  }
}
