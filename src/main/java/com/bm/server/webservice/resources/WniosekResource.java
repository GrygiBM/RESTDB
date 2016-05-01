package com.bm.server.webservice.resources;

import com.bm.server.database.HistoriazmianManager;
import com.bm.server.database.WniosekManager;
import com.bm.server.service.MerytorykaWniosku;
import com.bm.server.service.StanyWniosku;
import com.bm.server.model.Wniosek;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


//@Stateless
@Path("/wnioski")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class WniosekResource {


    @Inject
    private WniosekManager wniosekManager;

    @Inject
    private HistoriazmianManager historiazmianManager;

    @GET
    public Response getWszystkieWnioski(@QueryParam("nazwa") String nazwa,
                                        @QueryParam("stan") int stan) {


        if (nazwa != null && stan > 0)
            return Response.status(Response.Status.OK).entity(wniosekManager.getByNameAndStanWniosek(nazwa, stan)).build();

        if (stan > 0)
            return Response.status(Response.Status.OK).entity(wniosekManager.getByStanWniosek(stan)).build();


        if (nazwa != null)
            return Response.status(Response.Status.OK).entity(wniosekManager.getByNameWniosek(nazwa)).build();

        if (stan > 0)
            return Response.status(Response.Status.OK).entity(wniosekManager.getByStanWniosek(stan)).build();


        return Response.status(Response.Status.OK).entity(wniosekManager.getAllWnioski()).build();
    }


    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(Wniosek entity) {

        if (entity.getNazwa().trim().isEmpty() || entity.getTresc().isEmpty())
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();

        wniosekManager.addWniosek(entity);
        historiazmianManager.addHistory(entity.getId(), StanyWniosku.StanType.CREATED.name(), entity.getInfo());

        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, Wniosek entity) {

        if (StanyWniosku.nastepstwoStanow(wniosekManager.find(id).getStan(), entity.getStan())) {
            if (MerytorykaWniosku.sprawdzPoprawnoscDanych(entity, wniosekManager.find(id))) {
                wniosekManager.editWniosek(entity);
                historiazmianManager.addHistory(entity.getId(), StanyWniosku.StanType.valueOf(entity.getStan()).name(), entity.getInfo());
                return Response.status(Response.Status.ACCEPTED).build();
            }
        }
        return Response.status(Response.Status.NOT_MODIFIED).build();


    }

    @GET
    @Path("{from}/{to}")
    public List<Wniosek> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return wniosekManager.findRange(new int[]{from, to});
    }


    @GET
    @Path("{id}")
    public Response find(@PathParam("id") Integer id) {
        return Response.status(Response.Status.OK).entity(wniosekManager.find(id)).build();

    }

}
