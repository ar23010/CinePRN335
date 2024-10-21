package sv.edu.ues.occ.ingenieria.prn335_2024.cineprn335.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cineprn335.entity.Programacion;

import java.io.Serializable;

@Stateless
@LocalBean
public class ProgramacionBean extends AbstractDataPersistence<Programacion> implements Serializable {
    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public ProgramacionBean() {super( Programacion.class); }

    @Override
    public EntityManager getEntityManager() { return em; }
}
