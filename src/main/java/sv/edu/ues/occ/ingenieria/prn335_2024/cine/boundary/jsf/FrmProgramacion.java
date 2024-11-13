package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.PeliculaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.ProgramacionBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.SalaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Programacion;

import java.io.Serializable;

public class FrmProgramacion extends AbstractForm<Programacion> implements Serializable {

    @Inject
    ProgramacionBean prBean;

    @Inject
    FacesContext facesContext;

    @Inject
    SalaBean sBean;

    @Inject
    PeliculaBean pBean;



    @Override
    protected Object getId(Programacion Object) {
        return Object.getIdProgramacion();
    }

    @Override
    protected AbstractDataPersistence<Programacion> getDataBean() {
        return prBean;
    }

    @Override
    protected FacesContext facesContext() {
        return facesContext;
    }

    @Override
    protected Programacion createNewRegistro() {
        return new Programacion();
    }

    @Override
    public String buscarIdPorRegistro(Programacion entity) {
        return "";
    }

    @Override
    public Programacion buscarRegistroPorId(String id) {
        return null;
    }

    @Override
    public String getTituloDePagina() {
        return "Programación";
    }
}
