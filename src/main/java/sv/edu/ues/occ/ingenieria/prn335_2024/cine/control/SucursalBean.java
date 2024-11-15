package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Pelicula;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Sucursal;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@LocalBean
public class SucursalBean extends AbstractDataPersistence<Sucursal> implements Serializable {
    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public SucursalBean() {super( Sucursal.class); }

    @Override
    public EntityManager getEntityManager() { return em; }

    public List<Sucursal> findAll(int first, int max) {
        try{
            TypedQuery<Sucursal> q = em.createNamedQuery("Sucursal.findAll", Sucursal.class);
            q.setFirstResult(first);
            q.setMaxResults(max);
            return q.getResultList();
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage());
        }
        return List.of();
    }

}
