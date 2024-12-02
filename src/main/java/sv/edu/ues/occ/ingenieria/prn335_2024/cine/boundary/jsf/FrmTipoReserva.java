package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;


import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoReservaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoReserva;

import java.io.Serializable;

@Named
@ViewScoped
public class FrmTipoReserva extends AbstractFormulario<TipoReserva> implements Serializable {


    @Inject
    TipoReservaBean trBean;

    @Inject
    FacesContext facesContext;

    @Override
    protected Object getId(TipoReserva Object) {
        return Object.getIdTipoReserva();
    }

    @Override
    protected AbstractDataPersistence<TipoReserva> getDataBean() {
        return trBean;
    }

    @Override
    protected FacesContext facesContext() {
        return facesContext;
    }

    @Override
    protected TipoReserva createNewRegistro() {
        return new TipoReserva();
    }

    @Override
    public String buscarIdPorRegistro(TipoReserva entity) {
        return "";
    }

    @Override
    protected FacesContext getContext() {
        return null;
    }

    @Override
    public TipoReserva buscarRegistroPorId(String id) {
        return null;
    }

    @Override
    public String getTituloDePagina() {
        return "Tipo de Reserva";
    }
}
