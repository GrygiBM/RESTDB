package com.bm.server.webservice.resources;

import com.bm.server.database.HistoriazmianManager;
import com.bm.server.model.Historiazmian;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


//@Stateless
@Path("/historiazmian")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class HistoriazmianResource {

    @Inject
    private HistoriazmianManager historiazmianManager;


    @GET
    public Response getHistoria() {
        return Response.status(Response.Status.OK).entity(historiazmianManager.getAllHistory()).build();

    }

    @GET
    @Path("/wniosek/{idWniosek}")
    public Response getHistoriaWniosku(@PathParam("idWniosek") int idWniosek) {
        return Response.status(Response.Status.OK).entity(historiazmianManager.getByWniosek(idWniosek)).build();
    }


    @GET
    @Path("{id}")
    public Historiazmian find(@PathParam("id") Integer id) {

        return historiazmianManager.find(id);
    }

}
