package org.raziskovalec.web.jsf;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.raziskovalec.web.i18n.LocalizationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

public class MessageMap implements Map<String, String>
{
	
	private final LocalizationBean	localizationBean;
	private final MessageSource	messageSource;
	
	public MessageMap(final MessageSource messageSource, final LocalizationBean localizationBean)
	{
		this.messageSource = messageSource;
		this.localizationBean = localizationBean;
	}
	
	@Override
	public void clear()
	{
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean containsKey(final Object key)
	{
		try
		{
			messageSource.getMessage(key.toString(), null, localizationBean.getCurrentLocale());
		}
		catch (final NoSuchMessageException e)
		{
			return false;
		}
		return true;
	}
	
	@Override
	public boolean containsValue(final Object value)
	{
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Set<java.util.Map.Entry<String, String>> entrySet()
	{
		throw new UnsupportedOperationException();
	}
	
	@Override
	public String get(final Object key)
	{
		return messageSource.getMessage(key.toString(), null, localizationBean.getCurrentLocale());
	}
	
	@Override
	public boolean isEmpty()
	{
		return false;
	}
	
	@Override
	public Set<String> keySet()
	{
		throw new UnsupportedOperationException();
	}
	
	@Override
	public String put(final String key, final String value)
	{
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void putAll(final Map<? extends String, ? extends String> m)
	{
		throw new UnsupportedOperationException();
		
	}
	
	@Override
	public String remove(final Object key)
	{
		throw new UnsupportedOperationException();
	}
	
	@Override
	public int size()
	{
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Collection<String> values()
	{
		throw new UnsupportedOperationException();
	}
	
}
