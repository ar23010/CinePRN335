package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;


import jakarta.annotation.Resource;
import jakarta.ejb.Asynchronous;
import jakarta.ejb.LocalBean;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

import java.io.Serializable;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@LocalBean
public class ContadorBean implements Serializable {


    public enum TIPO_MENSAJE{
        CUENTA_RESPONSE, CUENTA_ERROR;
    }

    public enum TIPO_RESPUESTAS{
        ERROR, EXITO;
    }

    @Resource
    SessionContext sessionContext;


    @Asynchronous
    public void contarDespacio(int actual, UUID identificador, Consumer<JsonObject> callBack){
        JsonObjectBuilder cf = Json.createObjectBuilder();
        try{
            Thread.sleep(2000);
            cf.add("tipo_respuesta", TIPO_RESPUESTAS.EXITO.toString());
            cf.add("tipo_mensaje", TIPO_MENSAJE.CUENTA_RESPONSE.toString());
            cf.add("uuid", identificador.toString());
            cf.add("cuenta", ++actual);
            callBack.accept(cf.build());
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
            cf.add("tipo_respuesta", TIPO_RESPUESTAS.ERROR.toString());
            cf.add("tipo_mensaje", TIPO_MENSAJE.CUENTA_ERROR.toString());
            cf.add("uuid", identificador.toString());
            callBack.accept(cf.build());
            sessionContext.setRollbackOnly();
        }

    }

}
