package com.bm.server.webservice.resources;

import com.bm.server.database.HistoryModificationsManager;
import com.bm.server.model.Historiazmian;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


//@Stateless
@Path("/historiazmian")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class HistoryModificationsResource {

    @Inject
    private HistoryModificationsManager historyModificationsManager;


    @GET
    public Response getHistory() {
        return Response.status(Response.Status.OK).entity(historyModificationsManager.getAllHistory()).build();

    }

    @GET
    @Path("/wniosek/{idWniosek}")
    public Response getHistoryRequest(@PathParam("idWniosek") int idWniosek) {
        return Response.status(Response.Status.OK).entity(historyModificationsManager.getByRequest(idWniosek)).build();
    }


    @GET
    @Path("{id}")
    public Historiazmian find(@PathParam("id") Integer id) {

        return historyModificationsManager.find(id);
    }

}
