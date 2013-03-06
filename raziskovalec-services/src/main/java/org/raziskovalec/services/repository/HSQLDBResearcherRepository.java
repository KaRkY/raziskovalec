package org.raziskovalec.services.repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.joda.time.LocalDate;
import org.raziskovalec.Name;
import org.raziskovalec.base.SqlUtil;
import org.raziskovalec.domain.Researcher;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

public class HSQLDBResearcherRepository implements ResearcherRepository {

  private final NamedParameterJdbcTemplate jdbcTemplate;

  public HSQLDBResearcherRepository(final DataSource dataSource) {
    jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }

  @Override
  @Transactional
  public void delete(final int id) {
    jdbcTemplate.update("DELETE FROM PUBLIC.RESEARCHERS\n" +
        "WHERE ID = :id", new MapSqlParameterSource("id", id));
  }

  @Override
  @Transactional
  public boolean edit(final int id, final Researcher researcher) {
    final int numberOfResearchers = jdbcTemplate.queryForInt("SELECT COUNT(*) FROM PUBLIC.RESEARCHERS WHERE ID = :id",
        new MapSqlParameterSource("id", id));

    if (numberOfResearchers < 1) return false;

    final MapSqlParameterSource parameterSource = new MapSqlParameterSource()
        .addValue("id", id)
        .addValue("name", researcher.getName().toString())
        .addValue("lastName", researcher.getLastName().toString())
        .addValue("email", researcher.getEmail())
        .addValue("telephoneNumber", researcher.getTelephoneNumber())
        .addValue("website", researcher.getWebsite())
        .addValue("dateOfBirth", SqlUtil.nullSafeConvert(researcher.getDateOdBirth()));

    jdbcTemplate.update("UPDATE PUBLIC.RESEARCHERS\n" +
        "SET NAME = :name,\n" +
        "LAST_NAME = :lastName,\n" +
        "EMAIL = :email,\n" +
        "TELEPHONE_NUMBER = :telephoneNumber,\n" +
        "WEBSITE = :website,\n" +
        "DATE_OF_BIRTH = :dateOfBirth\n" +
        "WHERE ID = :id", parameterSource);

    return true;
  }

  @Override
  @Transactional(readOnly = true)
  public Researcher get(final int id) {
    return jdbcTemplate.queryForObject("SELECT ID, NAME, LAST_NAME, EMAIL, DATE_OF_BIRTH, TELEPHONE_NUMBER, WEBSITE\n" +
        "FROM PUBLIC.RESEARCHERS WHERE ID = :id", new MapSqlParameterSource("id", id), new ResearcherRowMapper());
  }

  @Override
  @Transactional
  public void insert(final Researcher researcher) {

    final MapSqlParameterSource parameterSource = new MapSqlParameterSource()
        .addValue("name", researcher.getName().toString())
        .addValue("lastName", researcher.getLastName().toString())
        .addValue("email", researcher.getEmail())
        .addValue("telephoneNumber", researcher.getTelephoneNumber())
        .addValue("website", researcher.getWebsite())
        .addValue("dateOfBirth", SqlUtil.nullSafeConvert(researcher.getDateOdBirth()));

    jdbcTemplate.update(
        "INSERT INTO PUBLIC.RESEARCHERS " +
            "(NAME, LAST_NAME, EMAIL, DATE_OF_BIRTH, TELEPHONE_NUMBER, WEBSITE) " +
            "VALUES" +
            "(:name, :lastName, :email, :dateOfBirth, :telephoneNumber, :website)",
        parameterSource);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Researcher> listAll() {
    return jdbcTemplate.query("SELECT ID, NAME, LAST_NAME, EMAIL, DATE_OF_BIRTH, TELEPHONE_NUMBER, WEBSITE \n" +
        "FROM PUBLIC.RESEARCHERS",
        new MapSqlParameterSource(),
        new ResearcherRowMapper());
  }

  private final class ResearcherRowMapper implements RowMapper<Researcher> {
    @Override
    public Researcher mapRow(final ResultSet rs, final int rowNum) throws SQLException {
      final Researcher result = new Researcher(rs.getInt("ID"));

      result.setName(Name.valueOf(rs.getString("NAME")));
      result.setLastName(Name.valueOf(rs.getString("LAST_NAME")));
      result.setEmail(rs.getString("EMAIL"));
      result.setTelephoneNumber(rs.getString("TELEPHONE_NUMBER"));
      result.setWebsite(rs.getString("WEBSITE"));

      final Date dateOfBirth = rs.getDate("DATE_OF_BIRTH");
      if (dateOfBirth != null) {
        result.setDateOdBirth(LocalDate.fromDateFields(dateOfBirth));
      }

      return result;
    }
  }
}
