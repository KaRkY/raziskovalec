package org.raziskovalec.services.repository;

import java.util.List;

import org.raziskovalec.domain.Researcher;
import org.springframework.transaction.annotation.Transactional;

public interface ResearcherRepository {

  @Transactional
  public abstract void insert(Researcher researcher);

  int count();

  void delete(int id);

  boolean edit(int id, Researcher researcher);

  Researcher get(int id);

  List<Researcher> listAll();

  List<Researcher> listPaged(int pageNum, int resultsPerPage);

}
