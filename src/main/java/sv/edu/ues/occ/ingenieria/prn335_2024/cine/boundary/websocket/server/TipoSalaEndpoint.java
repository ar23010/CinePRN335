package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.websocket.server;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@ServerEndpoint("/notificadortiposala")
@ApplicationScoped
@Named
public class TipoSalaEndpoint implements Serializable {

    @Inject
    ManejadorSesionesWS manejadorSesiones;

    //Se hacen anotaciones en los eventos, abrir, mensaje y cerrar por el momento

    @OnOpen
    public void abrir(Session session) {
        System.out.println("Conecto");
        manejadorSesiones.agregarSession(session);

    }

    @OnMessage
    public void propagarMensaje(Session session, String mensaje) {
     for(Session se: this.manejadorSesiones.getSESIONES()){
         if(se != null && se.isOpen()){
             try {
                 se.getBasicRemote().sendText(mensaje);
             }catch (Exception e){
                 Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage());
             }
         }else{

             }
     }

    }

    @OnClose
    public void cerrar(Session session) {
        manejadorSesiones.eliminarSession(session);
    }


}