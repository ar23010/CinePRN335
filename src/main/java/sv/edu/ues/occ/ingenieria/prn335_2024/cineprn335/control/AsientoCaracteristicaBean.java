package sv.edu.ues.occ.ingenieria.prn335_2024.cineprn335.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cineprn335.entity.AsientoCaracteristica;

import java.io.Serializable;

@Stateless
@LocalBean
public class AsientoCaracteristicaBean extends AbstractDataPersistence<AsientoCaracteristica> implements Serializable {
    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public AsientoCaracteristicaBean() {super( AsientoCaracteristica.class); }

    @Override
    public EntityManager getEntityManager() { return em; }
}