package org.raziskovalec.web;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.context.TilesRequestContext;
import org.apache.tiles.preparer.ViewPreparer;

public class TilesAttributeRequestContextPreparer implements ViewPreparer {

    @Override
    public void execute(final TilesRequestContext tilesContext,
	    final AttributeContext attributeContext) {
	/*for (final String name : attributeContext.getLocalAttributeNames())
	{
	    System.out.println(name);
	    tilesContext.getRequestScope().put(name,
		    attributeContext.getLocalAttribute(name));
	}*/
	
	tilesContext.getRequestScope().put("title",
		    attributeContext.getLocalAttribute("title"));
    }

}
