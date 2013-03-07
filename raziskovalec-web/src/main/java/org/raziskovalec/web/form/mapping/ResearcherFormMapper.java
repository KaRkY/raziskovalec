package org.raziskovalec.web.form.mapping;

import org.raziskovalec.Name;
import org.raziskovalec.base.ObjectsUtil;
import org.raziskovalec.domain.Researcher;
import org.raziskovalec.web.form.ResearcherForm;

public class ResearcherFormMapper {

  public static ResearcherForm mapToForm(final Researcher researcher) {
    final ResearcherForm researcherForm = new ResearcherForm();
    researcherForm.setName(researcher.getName().toString());
    researcherForm.setLastName(researcher.getLastName().toString());
    researcherForm.setEmail(researcher.getEmail());
    researcherForm.setTelephoneNumber(researcher.getTelephoneNumber());
    researcherForm.setWebsite(researcher.getWebsite());
    researcherForm.setDateOfBirth(researcher.getDateOfBirth());

    return researcherForm;
  }

  public static Researcher mapToResearcher(final ResearcherForm researcherForm, final Integer id) {
    final Researcher researcher = new Researcher(id);
    researcher.setName(Name.valueOf(researcherForm.getName()));
    researcher.setLastName(Name.valueOf(researcherForm.getLastName()));
    researcher.setDateOfBirth(researcherForm.getDateOfBirth());
    researcher.setEmail(ObjectsUtil.nullForEmpty(researcherForm.getEmail()));
    researcher.setTelephoneNumber(ObjectsUtil.nullForEmpty(researcherForm.getTelephoneNumber()));
    researcher.setWebsite(ObjectsUtil.nullForEmpty(researcherForm.getWebsite()));

    return researcher;
  }
}
