package org.raziskovalec.services.rest;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.raziskovalec.domain.Researcher;
import org.raziskovalec.services.repository.ResearcherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/researcher")
@Produces("application/json")
public class ResearcherServices {
  private final Logger               logger = LoggerFactory.getLogger(getClass());
  private final ResearcherRepository repo;

  public ResearcherServices(final ResearcherRepository repo) {
    this.repo = repo;
  }

  @DELETE
  @Path("/{id}")
  public Response delete(@PathParam("id") final int id) {
    repo.delete(id);

    return Response.ok().build();
  }

  @POST
  @Path("{id}")
  public Response edit(@PathParam("id") final int id, final Researcher researcher) {
    if (repo.edit(id, researcher)) return Response.ok().build();
    else return Response.notModified().build();
  }

  @GET
  @Path("/{id}")
  public Response get(@PathParam("id") final int id) {

    return Response.ok().entity(repo.get(id)).build();
  }

  @GET
  public Response listAll() {
    final List<Researcher> allResearchers = repo.listAll();

    return Response.ok().entity(allResearchers).build();
  }

  @PUT
  public Response save(final Researcher researcher) {

    repo.insert(researcher);

    return Response.ok().build();
  }
}
