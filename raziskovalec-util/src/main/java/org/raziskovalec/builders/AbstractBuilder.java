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
package org.raziskovalec.builders;


/**
 * Abstraktni razred za builderje
 *
 * @author Rene Svetina
 */
public abstract class AbstractBuilder<V extends Object, T extends AbstractBuilder<?, ?>>
{
    private V value;

    /**
     * Default constructor
     */
    public AbstractBuilder()
    {
        setValue(getObject());
    }

    /**
     * Vrne novi objekt katerega želimo graditi.
     *
     * @return novi objekt za grajenje
     */
    protected abstract V getObject();

    /**
     * Vrne samega sebe zaradi narave generikov v javi.
     *
     * @return sebe
     */
    @SuppressWarnings("unchecked")
    protected T self()
    {
        return (T) this;
    }

    /**
     * Vrne trenutno vrednost objekta ki ga gradimo
     *
     * @return trenutno vrednost
     */
    protected V getValue()
    {
        return value;
    }

    private void setValue(final V value)
    {
        this.value = value;
    }

    /**
     * Vrne zgrajen objekt.
     *
     * @return zgrajen objekt
     */
    public V build()
    {
        final V returnValue = getValue();
        setValue(getObject());
        return returnValue;
    }
}
