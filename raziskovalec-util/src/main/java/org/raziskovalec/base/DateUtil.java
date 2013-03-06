package org.raziskovalec.base;

import java.util.Locale;

import org.joda.time.ReadablePartial;
import org.joda.time.format.DateTimeFormat;

public final class DateUtil {
  private final Locale locale;

  public DateUtil(final Locale locale) {
    this.locale = locale;
  }

  public String formatPattern(final ReadablePartial date, final String pattern) {
    if (date == null) return "";
    return DateTimeFormat.forPattern(pattern).withLocale(locale).print(date);
  }

  public String formatStyle(final ReadablePartial date, final String style) {
    if (date == null) return "";
    return DateTimeFormat.forStyle(style).withLocale(locale).print(date);
  }
}
