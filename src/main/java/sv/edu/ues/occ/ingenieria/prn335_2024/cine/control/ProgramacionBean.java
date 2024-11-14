package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Pelicula;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.PeliculaCaracteristica;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Programacion;

import java.io.Serializable;
import java.util.List;
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


    public List<Programacion> findByIdProgramacion(final long idProgramacion, int first, int max) {
        try{
            TypedQuery<Programacion> q = em.createNamedQuery("Programacion.findByIdProgramacion", Programacion.class);
            q.setParameter("idProgramacion", idProgramacion);
            q.setFirstResult(first);
            q.setMaxResults(max);
            return q.getResultList();
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage());
        }
        return List.of();
    }


    public int countProgramacion(final Long idProgramacion) {
        try {
            TypedQuery<Long> q = em.createNamedQuery("Programacion.countByIdProgramacion", Long.class);
            q.setParameter("idProgramacion", idProgramacion);
            return q.getSingleResult().intValue();
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage());
        }
        return 0;
    }

    /*public List<Pelicula> listPeliculas(final long idSala, int first, int max) {
        try{
            TypedQuery<Pelicula> q = em.createNamedQuery("Programacion.findAll",  Pelicula.class);
            q.setParameter("idSala", idSala);
            q.setFirstResult(first);
            q.setMaxResults(max);
            return q.getResultList();
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage());
        }
        return List.of();
    }*/

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
}
