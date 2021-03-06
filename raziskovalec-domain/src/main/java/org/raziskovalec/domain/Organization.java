package org.raziskovalec.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;

import org.raziskovalec.Name;

import com.google.common.collect.Sets;

public class Organization {
  private final Name            name;
  private final String          email;
  private final Set<Researcher> employees = Sets.newLinkedHashSet();

  public Organization(final Name name, final String email) {
    this.name = name;
    this.email = email;
  }

  public String email() {
    return email;
  }

  public void fire(final Researcher researcher) {
    employees.remove(researcher);
  }

  public Name name() {
    return name;
  }

  public void recruit(final Researcher researcher) {
    employees.add(checkNotNull(researcher, "Researcher cannot be null."));
  }
}
