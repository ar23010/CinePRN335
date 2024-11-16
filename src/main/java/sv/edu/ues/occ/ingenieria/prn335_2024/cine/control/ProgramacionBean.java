package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Provider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Programacion;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@LocalBean
public class ProgramacionBean extends AbstractDataPersistence<Programacion> implements Serializable {
    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public ProgramacionBean() {super( Programacion.class); }

    @Override
    public EntityManager getEntityManager() { return em; }



    public List<Programacion> findAll(int first, int max) {
        try{
            TypedQuery<Programacion> q = em.createNamedQuery("Programacion.findAll", Programacion.class);
            q.setFirstResult(first);
            q.setMaxResults(max);
            return q.getResultList();
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage());
        }
        return List.of();
    }

    public List<Programacion> findByDate(final OffsetDateTime desde, final OffsetDateTime hasta) {
        try {
            TypedQuery<Programacion> q = em.createNamedQuery("Programacion.findByDate", Programacion.class);
            q.setParameter("desde", desde);
            q.setParameter("hasta", hasta);
            return q.getResultList();
        }catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
        }
        return List.of();
    }

    public Map<Long, Programacion> getProgramacionesAsMap() {
        List<Programacion> programaciones = findAll(0,1000);
        Map<Long, Programacion> programacionesMap = new HashMap<>();

        for (Programacion programacion : programaciones) {
            programacionesMap.put(programacion.getIdProgramacion(), programacion);
        }

        return programacionesMap;
    }
}
