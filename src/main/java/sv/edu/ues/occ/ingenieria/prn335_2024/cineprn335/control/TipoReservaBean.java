package sv.edu.ues.occ.ingenieria.prn335_2024.cineprn335.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cineprn335.entity.TipoReserva;

import java.io.Serializable;

public class TipoReservaBean extends AbstractDataPersist<TipoReserva> implements Serializable {

    @PersistenceContext(unitName = "CinePU")
    EntityManager em;


    public TipoReservaBean() {
        super(TipoReserva.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

}
