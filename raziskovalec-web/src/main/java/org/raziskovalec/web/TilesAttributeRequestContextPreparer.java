package org.raziskovalec.web;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.context.TilesRequestContext;
import org.apache.tiles.preparer.ViewPreparer;

public class TilesAttributeRequestContextPreparer implements ViewPreparer {

    @Override
    public void execute(final TilesRequestContext tilesContext,
	    final AttributeContext attributeContext) {
	for (final String name : attributeContext.getLocalAttributeNames())
	    tilesContext.getRequestScope().put(name,
		    attributeContext.getLocalAttribute(name));
    }

}
