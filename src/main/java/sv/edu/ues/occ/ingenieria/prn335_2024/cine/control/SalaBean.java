package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Pelicula;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Sala;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@LocalBean
public class SalaBean extends AbstractDataPersistence<Sala> implements Serializable {

@PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public SalaBean(){
        super(Sala.class);

    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }


    public List<Sala> findAll(int first, int max) {
        try{
            TypedQuery<Sala> q = em.createNamedQuery("Sala.findAll", Sala.class);
            q.setFirstResult(first);
            q.setMaxResults(max);
            return q.getResultList();
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage());
        }
        return List.of();
    }
}


