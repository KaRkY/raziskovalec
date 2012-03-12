package org.raziskovalec.domain.value;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Postcode for address.
 * 
 * @author Rene Svetina
 * 
 */
public final class Postcode
{
	private static final int HASH_PRIME = 17;
	private static final int HASH_MULTIPLIER = 31;
	private final String code;
	private final String name;
	private static Pattern postalPattern;

	static
	{
		postalPattern = Pattern.compile("^\\[([A-Za-z0-9]+)\\](.*)$");
	}

	private Postcode(final String code, final String name)
	{
		checkNotNull(code, "Code should not be null.");
		checkNotNull(name, "name should not be null.");
		checkArgument(!code.isEmpty(), "Code should not be empty.");
		checkArgument(!name.isEmpty(), "Name should not be empty.");

		this.code = code;
		this.name = name;
	}

	/**
	 * Postal code.
	 * 
	 * @return Postal code.
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * Postal name.
	 * 
	 * @return Postal name.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Postalcode.
	 * 
	 * @param code
	 *            postal code
	 * @param name
	 *            postal name
	 * @return Postcode
	 */
	public static Postcode valueOf(final String code, final String name)
	{
		return new Postcode(code, name);
	}

	/**
	 * Postcode from format string [code]name.
	 * 
	 * @param postalCode
	 *            formated string.
	 * @return Postcode
	 */
	public static Postcode valueOf(final String postalCode)
	{
		Matcher postalMatcher = postalPattern.matcher(postalCode);
		checkArgument(postalMatcher.matches(), "Wrong postalCode format, format should be [<code>]<name>");

		return valueOf(postalMatcher.group(1), postalMatcher.group(2));
	}

	@Override
	public int hashCode()
	{
		final HashCodeBuilder builder = new HashCodeBuilder(HASH_PRIME, HASH_MULTIPLIER);

		builder.append(code);

		return builder.build();
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (obj == null)
		{
			return false;
		}
		if (obj == this)
		{
			return true;
		}
		if (obj.getClass() != getClass())
		{
			return false;
		}
		final Postcode that = (Postcode) obj;
		final EqualsBuilder builder = new EqualsBuilder();

		builder.append(code, that.code);

		return builder.isEquals();
	}

	@Override
	public String toString()
	{
		return String.format("[%s]%s", code, name);
	}
}
