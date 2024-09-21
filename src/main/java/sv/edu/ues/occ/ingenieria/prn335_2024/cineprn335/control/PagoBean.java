package sv.edu.ues.occ.ingenieria.prn335_2024.cineprn335.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cineprn335.entity.Pago;
import sv.edu.ues.occ.ingenieria.prn335_2024.cineprn335.entity.Pelicula;

import java.io.Serializable;

@Stateless
@LocalBean
public class PagoBean extends AbstractDataPersist<Pago> implements Serializable {
    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public PagoBean() {super( Pago.class); }

    @Override
    public EntityManager getEntityManager() { return em; }
}
