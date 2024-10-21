package sv.edu.ues.occ.ingenieria.prn335_2024.cineprn335.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cineprn335.entity.FacturaDetalleSala;

import java.io.Serializable;

@Stateless
@LocalBean
public class FacturaDetalleSalaBean extends AbstractDataPersistence<FacturaDetalleSala> implements Serializable {
    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public FacturaDetalleSalaBean() {super( FacturaDetalleSala.class); }

    @Override
    public EntityManager getEntityManager() { return em; }
}
