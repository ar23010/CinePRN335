package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;


import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoProductoBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoProducto;

import java.io.Serializable;

@Named
@ViewScoped
public class FrmTipoProducto extends AbstractForm<TipoProducto> implements Serializable {

    @Inject
    TipoProductoBean tprBean;

    @Inject
    FacesContext facesContext;



    @Override
    protected Object getId(TipoProducto Object) {
        return Object.getIdTipoProducto();
    }

    @Override
    protected AbstractDataPersistence<TipoProducto> getDataBean() {
        return tprBean;
    }

    @Override
    protected FacesContext facesContext() {
        return facesContext;
    }

    @Override
    protected TipoProducto createNewRegistro() {
        return new TipoProducto();
    }
}
