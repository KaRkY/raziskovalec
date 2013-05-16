package org.raziskovalec.web.services;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.raziskovalec.domain.Researcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResearcherService {
  private final Logger    logger = LoggerFactory.getLogger(getClass());

  private final WebClient client;

  public ResearcherService(final WebClient client) {
    this.client = client;
  }

  public int count() {
    final Response response = getCopy().path("/researcher/count").get();

    if (isResponseOK(response)) return response.readEntity(Integer.class);

    logger.error("Response error: {}", prittyPrintResponseStatus(response.getStatusInfo()));
    throw new RuntimeException("Error in response");
  }

  public void delete(final int id) {
    final Response response = getCopy().path("/researcher/{id}", id).delete();

    if (isResponseOK(response)) {
      logger.error("Response error: {}", prittyPrintResponseStatus(response.getStatusInfo()));
      throw new RuntimeException("Error in response");
    }
  }

  public void edit(final Researcher researcher) {
    final Response response = getCopy().path("/researcher").post(researcher);

    if (!isResponseOK(response)) {
      logger.error("Response error: {}", prittyPrintResponseStatus(response.getStatusInfo()));
      throw new RuntimeException("Error in response");
    }
  }

  public Researcher get(final int id) {
    final Response response = getCopy().path("/researcher/{id}", id).get();

    if (isResponseOK(response)) return response.readEntity(Researcher.class);

    logger.error("Response error: {}", prittyPrintResponseStatus(response.getStatusInfo()));
    throw new RuntimeException("Error in response");
  }

  public List<Researcher> listPaged(final int pageNum, final int resultsPerPage) {
    final Response response = getCopy().path("/researcher/{pageNum}/{resultsPerPage}",
        pageNum,
        resultsPerPage)
        .get();

    if (isResponseOK(response)) return response.readEntity(new GenericType<List<Researcher>>() {
    });

    logger.error("Response error: {}", prittyPrintResponseStatus(response.getStatusInfo()));
    throw new RuntimeException("Error in response");
  }

  public void save(final Researcher researcher) {
    final Response response = getCopy().path("/researcher").put(researcher);

    if (!isResponseOK(response)) {
      logger.error("Response error: {}", prittyPrintResponseStatus(response.getStatusInfo()));
      throw new RuntimeException("Error in response");
    }
  }

  private WebClient getCopy() {
    return WebClient.fromClient(client, true);
  }

  private boolean isResponseOK(final Response response) {
    return response.getStatusInfo().getStatusCode() == Status.OK.getStatusCode();
  }

  private String prittyPrintResponseStatus(final StatusType statusType) {
    return String.format("Response code: %s, phrase: %s", statusType.getStatusCode(), statusType.getReasonPhrase());
  }
}
