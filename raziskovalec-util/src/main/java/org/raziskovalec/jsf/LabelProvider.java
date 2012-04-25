package org.raziskovalec.jsf;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ListenerFor;
import javax.faces.event.PreValidateEvent;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;

@ListenerFor(sourceClass = HtmlOutputLabel.class, systemEventClass = PreValidateEvent.class)
public class LabelProvider implements
		SystemEventListener
{

	@Override
	public boolean isListenerForSource(final Object source)
	{
		return true;
	}

	@Override
	public void processEvent(final SystemEvent event) throws AbortProcessingException
	{
		HtmlOutputLabel outputLabel = (HtmlOutputLabel) event.getSource();
		UIComponent target = outputLabel.findComponent(outputLabel.getFor());
		if (target != null)
		{
			target.getAttributes().put("label",
					outputLabel.getValue());
			System.out.println(outputLabel.getValue());
		}

	}
}
