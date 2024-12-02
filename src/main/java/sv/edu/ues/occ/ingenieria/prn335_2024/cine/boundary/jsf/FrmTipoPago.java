package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;


import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoPagoBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoPago;

import java.io.Serializable;

@Named
@ViewScoped
public class FrmTipoPago extends AbstractFormulario<TipoPago> implements Serializable {

    @Inject
    TipoPagoBean tpBean;

    @Inject
    FacesContext facesContext;



    @Override
    protected Object getId(TipoPago Object) {
        return Object.getIdTipoPago();
    }

    @Override
    protected AbstractDataPersistence<TipoPago> getDataBean() {
        return tpBean;
    }

    @Override
    protected FacesContext facesContext() {
        return facesContext;
    }

    @Override
    protected TipoPago createNewRegistro() {
        return new TipoPago();
    }

    @Override
    public String buscarIdPorRegistro(TipoPago entity) {
        return "";
    }

    @Override
    protected FacesContext getContext() {
        return null;
    }

    @Override
    public TipoPago buscarRegistroPorId(String id) {
        return null;
    }

    @Override
    public String getTituloDePagina() {
        return "Tipo de Pago";
    }
}
