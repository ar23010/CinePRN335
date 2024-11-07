package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;


import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoAsientoBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoAsiento;

import java.io.Serializable;

@Named
@ViewScoped
public class FrmTipoAsiento extends AbstractForm<TipoAsiento> implements Serializable {

   @Inject
   TipoAsientoBean taBean;

   @Inject
   FacesContext facesContext;


    @Override
    protected Object getId(TipoAsiento Object) {
        return Object.getIdTipoAsiento();
    }

    @Override
    protected AbstractDataPersistence<TipoAsiento> getDataBean() {
        return taBean;
    }

    @Override
    protected FacesContext facesContext() {
        return facesContext;
    }

    @Override
    protected TipoAsiento createNewRegistro() {
        return new TipoAsiento();
    }
}
