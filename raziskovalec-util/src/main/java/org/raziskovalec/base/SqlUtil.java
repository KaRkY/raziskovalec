package org.raziskovalec.base;

import java.util.Date;

import org.joda.time.LocalDate;

public final class SqlUtil {

  private SqlUtil() {
  }

  public static Date nullSafeConvert(final LocalDate date) {
    return date != null ? date.toDate() : null;
  }
}
