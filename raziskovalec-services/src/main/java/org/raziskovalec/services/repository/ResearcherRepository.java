package org.raziskovalec.services.repository;

import java.util.List;

import org.raziskovalec.domain.Researcher;
import org.springframework.transaction.annotation.Transactional;

public interface ResearcherRepository {

  @Transactional
  void insert(Researcher researcher);

  @Transactional(readOnly = true)
  long count();

  @Transactional
  void delete(int id);

  @Transactional
  boolean edit(Researcher researcher);

  @Transactional(readOnly = true)
  Researcher get(int id);

  @Transactional(readOnly = true)
  List<Researcher> listAll();

  @Transactional(readOnly = true)
  List<Researcher> listPaged(int pageNum, int resultsPerPage);

}
