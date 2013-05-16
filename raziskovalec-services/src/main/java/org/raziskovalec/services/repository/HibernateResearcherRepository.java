package org.raziskovalec.services.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.raziskovalec.domain.Researcher;
import org.springframework.transaction.annotation.Transactional;

public class HibernateResearcherRepository implements ResearcherRepository {

  private final SessionFactory sessionFactory;

  public HibernateResearcherRepository(final SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  private Session getCurrentSession() {
    return sessionFactory.getCurrentSession();
  }

  @Override
  @Transactional
  public void insert(final Researcher researcher) {
    getCurrentSession().persist(researcher);
  }

  @Override
  @Transactional(readOnly = true)
  public long count() {
    return (long) getCurrentSession().createCriteria(Researcher.class).setProjection(Projections.rowCount())
        .uniqueResult();
  }

  @Override
  @Transactional
  public void delete(final int id) {
    getCurrentSession().delete(getCurrentSession().get(Researcher.class, id));
  }

  @Override
  @Transactional
  public boolean edit(final Researcher researcher) {
    getCurrentSession().merge(researcher);
    return true;
  }

  @Override
  @Transactional(readOnly = true)
  public Researcher get(final int id) {
    return (Researcher) getCurrentSession().get(Researcher.class, id);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Researcher> listAll() {
    @SuppressWarnings("unchecked") List<Researcher> researchers = getCurrentSession().createCriteria(Researcher.class)
        .list();
    return researchers;
  }

  @Override
  @Transactional(readOnly = true)
  public List<Researcher> listPaged(final int pageNum, final int resultsPerPage) {
    @SuppressWarnings("unchecked") List<Researcher> researchers = getCurrentSession().createCriteria(Researcher.class)
        .setFetchSize(resultsPerPage).setFirstResult(pageNum * resultsPerPage).list();
    return researchers;
  }
}
