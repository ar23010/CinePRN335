package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Sala;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SalaBean extends AbstractDataPersistence<Sala> implements Serializable {

@PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public SalaBean(){
        super(Sala.class);

    }

    @Override
    public EntityManager getEntityManager() {
        return null;
    }


    public List<Sala> findByIdTipoSala(Integer idTipoSala, int first, int max){
        if (idTipoSala != null){
          try{
              if(em != null){
                  Query q = em.createNamedQuery("Sala.findByIdTipoSala");
                  q.setParameter("idTipoSala", idTipoSala);
                  return q.getResultList();

              }
          }catch (Exception e){
              Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
          }
        }
        return Collections.emptyList();
    }
}

