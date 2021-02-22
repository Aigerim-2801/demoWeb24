package controllers;
import entities.Medicines;
import repositories.interfaces.IMedicinesRepository;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
@Path("medicines")
public class MedicinesController {

    @Inject
    private IMedicinesRepository repo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMedicines() {
        List<Medicines> medicines;
        try {
            medicines = repo.getAllMedicines();
        } catch (ServerErrorException ex) {
            return Response
                    .status(500).entity(ex.getMessage()).build();
        }

        return Response
                .status(Response.Status.OK)
                .entity(medicines)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMedicines(Medicines medicines) {
        boolean created;
        try {
            created = repo.createMedicines(medicines);
        } catch (ServerErrorException ex) {
            return Response.serverError().entity(ex.getMessage()).build();
        }

        if (!created) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Medicines creation was failed!")
                    .build();
        }

        return Response
                .status(Response.Status.CREATED)
                .entity("Medicines was created!")
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedicinesById(@PathParam("id") int id) {
        Medicines medicines;
        try {
            medicines = repo.getMedicinesById(id);
        } catch (ServerErrorException ex) {
            return Response
                    .status(500).entity(ex.getMessage()).build();
        }

        if (medicines == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Medicines does not exist!")
                    .build();
        }

        return Response
                .status(Response.Status.OK)
                .entity(medicines)
                .build();
    }
}