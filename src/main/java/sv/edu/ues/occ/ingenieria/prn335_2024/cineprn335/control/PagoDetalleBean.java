package sv.edu.ues.occ.ingenieria.prn335_2024.cineprn335.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cineprn335.entity.PagoDetalle;

import java.io.Serializable;

@Stateless
@LocalBean
public class PagoDetalleBean extends AbstractDataPersistence<PagoDetalle> implements Serializable {
    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public PagoDetalleBean() {super( PagoDetalle.class); }

    @Override
    public EntityManager getEntityManager() { return em; }
}
