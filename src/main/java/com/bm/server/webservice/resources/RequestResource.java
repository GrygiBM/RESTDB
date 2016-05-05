package com.bm.server.webservice.resources;

import com.bm.server.database.HistoryModificationsManager;
import com.bm.server.database.RequestManager;
import com.bm.server.model.Wniosek;
import com.bm.server.service.StateRequest;
import com.bm.server.service.ValidateRequest;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


//@Stateless
@Path("/wnioski")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class RequestResource {


    @Inject
    private RequestManager requestManager;

    @Inject
    private HistoryModificationsManager historyModificationsManager;

    @GET
    public Response getAllRequests(@QueryParam("nazwa") String nazwa,
                                   @QueryParam("stan") int stan) {


        if (nazwa != null && stan > 0)
            return Response.status(Response.Status.OK).entity(requestManager.getByNameAndStateRequest(nazwa, stan)).build();

        if (stan > 0)
            return Response.status(Response.Status.OK).entity(requestManager.getByStateRequest(stan)).build();


        if (nazwa != null)
            return Response.status(Response.Status.OK).entity(requestManager.getByNameRequest(nazwa)).build();

        if (stan > 0)
            return Response.status(Response.Status.OK).entity(requestManager.getByStateRequest(stan)).build();


        return Response.status(Response.Status.OK).entity(requestManager.getAllRequests()).build();
    }


    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(Wniosek entity) {

        if (entity.getNazwa().trim().isEmpty() || entity.getTresc().isEmpty())
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();

        requestManager.addRequest(entity);
        historyModificationsManager.addHistory(entity.getId(), StateRequest.StateType.CREATED.name(), entity.getInfo());

        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, Wniosek entity) {


        if (ValidateRequest.Validate(requestManager.find(id), entity)) {
            requestManager.editRequest(entity);
            historyModificationsManager.addHistory(entity.getId(), StateRequest.StateType.valueOf(entity.getStan()).name(), entity.getInfo());
            return Response.status(Response.Status.ACCEPTED).build();
        }
        return Response.status(Response.Status.NOT_MODIFIED).build();


    }

    @GET
    @Path("{from}/{to}")
    public List<Wniosek> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return requestManager.findRange(new int[]{from, to});
    }


    @GET
    @Path("{id}")
    public Response find(@PathParam("id") Integer id) {
        return Response.status(Response.Status.OK).entity(requestManager.find(id)).build();

    }

}
