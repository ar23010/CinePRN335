package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.websocket.server;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.websocket.Session;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
@Named
public class ManejadorSesionesWS implements Serializable {

    private static Set<Session> SESIONES = new HashSet<>();

    public void agregarSession(Session session) {
        SESIONES.add(session);
    }

    public void eliminarSession(Session session) {
        SESIONES.remove(session);
    }


    public Set<Session> getSESIONES() {
        return SESIONES;
    }
}
