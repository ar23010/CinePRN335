package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;


import jakarta.faces.push.Push;
import jakarta.faces.push.PushContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.json.JsonObject;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.ContadorBean;

import java.io.Serializable;
import java.util.UUID;

@Named
@ViewScoped
public class FrmWebSocket implements Serializable {


/*
    @Inject
    @Push(channel = "CanalOcc")
    PushContext pushContext;

    @Inject
    ContadorBean cBean;


    int cuenta=0;
    UUID uid;

    public int getCuenta() {
        return cuenta;
    }

    public void enviarMensaje() {
        uid = UUID.randomUUID();
        cBean.contarDespacio(cuenta, uid, mensaje->recibirMensaje(mensaje));
        pushContext.send("Mensaje enviado" + System.currentTimeMillis());

    }

    public void recibirMensaje(JsonObject respuesta){
        if(respuesta!=null){
            if(respuesta.getString("tipo_respuesta").equals(ContadorBean.TIPO_RESPUESTAS.EXITO.toString())&&respuesta.getString("uuid").equals(this.uid.toString())){
                cuenta = respuesta.getInt("cuenta");
                pushContext.send("Cuenta" + cuenta);

            }
        }
    }
*/
}
