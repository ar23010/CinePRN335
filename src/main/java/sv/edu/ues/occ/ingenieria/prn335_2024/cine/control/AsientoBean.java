package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Asiento;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Pelicula;

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



    public List<Asiento> findAll(int first, int max) {
        try{
            TypedQuery<Asiento> q = em.createNamedQuery("Asiento.findAll", Asiento.class);
            q.setFirstResult(first);
            q.setMaxResults(max);
            return q.getResultList();
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage());
        }
        return List.of();
    }
}
