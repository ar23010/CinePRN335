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
    public List<Asiento> obtenerAsientosDisponibles(Long idPelicula, String fecha) {
        String queryStr = "SELECT a.* " +
                "FROM asiento a " +
                "JOIN sala s ON a.idSala = s.idSala " +
                "JOIN reserva r ON s.idSala = r.idSala " +
                "WHERE r.idPelicula = :idPelicula " +
                "AND r.fecha = :fecha " +
                "AND NOT EXISTS (" +
                "    SELECT 1 " +
                "    FROM reserva_detalle rd " +
                "    WHERE rd.idAsiento = a.idAsiento " +
                "    AND rd.estado = 'CREADO'" +
                ")";

        Query query = em.createNativeQuery(queryStr, Asiento.class);
        query.setParameter("idPelicula", idPelicula);
        query.setParameter("fecha", fecha);


        List<Asiento> asientosDisponibles = query.getResultList();
        return asientosDisponibles;
    }
}
