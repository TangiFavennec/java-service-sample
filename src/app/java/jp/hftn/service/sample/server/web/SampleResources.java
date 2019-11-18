package jp.hftn.service.sample.server.web;

import jp.hftn.service.sample.client.ClientMapper;
import jp.hftn.service.sample.model.UserService;
import jp.hftn.service.sample.server.Paths;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.ok;
import static jp.hftn.service.sample.server.Paths.__;

@Path(__ + Paths.USERS)
public class SampleResources {

    private final ClientMapper clientMapper;
    private final UserService userService;

    @Inject
    public SampleResources(final ClientMapper clientMapper,
                           final UserService userService) {
        this.clientMapper = clientMapper;
        this.userService = userService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(__ + "{id}")
    public Response getUser(@PathParam("id") Integer userId) {
        return ok(clientMapper.toClientUser(userService.getUserById(userId))).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        return ok(clientMapper.toClientUsersResponse(userService.getUsers())).build();
    }

}