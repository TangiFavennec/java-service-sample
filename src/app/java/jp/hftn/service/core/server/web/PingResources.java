package jp.hftn.service.core.server.web;

import jp.hftn.service.core.server.Paths;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.ok;
import static jp.hftn.service.sample.server.Paths.__;

@Path(__ + Paths.STATUS)
public class PingResources {

    @GET
    public Response ping() {
        return ok("Status OK.").build();
    }
}
