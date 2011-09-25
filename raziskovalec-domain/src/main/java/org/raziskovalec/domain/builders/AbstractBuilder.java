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
package org.raziskovalec.domain.builders;

/**
 * @author Rene Svetina
 * 
 */
public abstract class AbstractBuilder<V extends Object, T extends AbstractBuilder<V, T>>
{
    private V value;
    
    /**
     * 
     */
    public AbstractBuilder()
    {
        setValue(getObject());
    }
    
    protected abstract V getObject();
    
    protected abstract T self();
    
    protected V getValue()
    {
        return value;
    }
    
    protected void setValue(final V value)
    {
        this.value = value;
    }
    
    public V build()
    {
        final V returnValue = getValue();
        setValue(getObject());
        return returnValue;
    }
}
