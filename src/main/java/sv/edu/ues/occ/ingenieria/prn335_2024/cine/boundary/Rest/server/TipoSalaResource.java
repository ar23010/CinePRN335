package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.Rest.server;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoSalaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoSala;

import java.awt.*;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("tiposala")
public class TipoSalaResource implements Serializable {

    @Inject
    TipoSalaBean tSBean;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findRange(
            @QueryParam("first")
            @DefaultValue("0")
            int firstResult,
            @QueryParam("max")
            @DefaultValue("3")
            int maxResult){
        try{
            if(firstResult >= 0 && maxResult > 0 && maxResult <= 50){
                List<TipoSala> encontrados = tSBean.findRange(firstResult, maxResult);
                Long total = tSBean.count();
                Response.ResponseBuilder builder = Response.ok(encontrados)
                .header("Total-Records", total)
                .type(MediaType.APPLICATION_JSON);
                return builder.build();
            }else {
                //422 contenido no procesado
                //findById: 333 -> 484
                return Response.status(422).header("Wrong-Parameter", "first:"+firstResult+",max:" + maxResult).build();
            }


        }catch (Exception e){

            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id){
    if(id!=null){
        try{
            TipoSala encontrado = tSBean.findById(id);
            if(encontrado!=null){
                Response.ResponseBuilder builder = Response.ok(encontrado).type(MediaType.APPLICATION_JSON);
                return builder.build();
            }
            return Response.status(404).header("Not-Parameter", "id:"+id).build();
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
    return Response.status(422).header("Wrong-Parameter", "id:"+id).build();
    }
}
