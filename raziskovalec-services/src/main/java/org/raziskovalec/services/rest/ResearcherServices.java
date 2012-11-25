package org.raziskovalec.services.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.raziskovalec.domain.Researcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/researcher")
@Produces("application/json")
public class ResearcherServices {
  private final Logger logger = LoggerFactory.getLogger(getClass());

  @POST
  @Path("/add")
  public Response saveResearcher(final Researcher researcher) {
    logger.trace("Researcher: {}", researcher.toString());
    return Response.ok().build();
  }
}
