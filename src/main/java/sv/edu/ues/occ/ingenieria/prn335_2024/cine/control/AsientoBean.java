package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Asiento;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.SalaCaracteristica;


import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@LocalBean
public class AsientoBean extends AbstractDataPersistence<Asiento> implements Serializable {
    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public AsientoBean() {super( Asiento.class); }

    @Override
    public EntityManager getEntityManager() { return em; }



    public List<Asiento> findByIdSala(final Integer idSala, int first, int max) {
        try{
            TypedQuery<Asiento> q = em.createNamedQuery("Asiento.findByIdSala", Asiento.class);
            q.setParameter("idSala", idSala);
            q.setFirstResult(first);
            q.setMaxResults(max);
            return q.getResultList();
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage());
        }
        return List.of();
    }

    public List<Asiento> findDisponiblesByIdProgramacion(final Long idProgramacion) {
        try {
            TypedQuery<Asiento> q = em.createNamedQuery("Asiento.findDisponiblesByIdProgramacion", Asiento.class);
            q.setParameter("idProgramacion", idProgramacion);
            return q.getResultList();
        }catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
        }
        return List.of();
    }

}
