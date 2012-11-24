package org.raziskovalec.services.test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.raziskovalec.Identifier;
import org.raziskovalec.Name;
import org.raziskovalec.domain.Researcher;

@Path("rene")
@Produces("application/json")
public class TestingService {

  @GET
  public Researcher getLala() {
    return new Researcher(Identifier.newId(), Name.valueOf("Rene"), Name.valueOf("Svetina"), "rene.svetina@gmail.com");
  }
}
