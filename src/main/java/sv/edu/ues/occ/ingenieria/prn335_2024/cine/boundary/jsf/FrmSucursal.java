package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.SucursalBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Sucursal;

import java.io.Serializable;

public class FrmSucursal  extends AbstractForm<Sucursal> implements Serializable {

    @Inject
    SucursalBean sBean;

    @Inject
    FacesContext facesContext;


    @Override
    protected Object getId(Sucursal Object) {
        return Object.getIdSucursal();
    }

    @Override
    protected AbstractDataPersistence<Sucursal> getDataBean() {
        return sBean;
    }

    @Override
    protected FacesContext facesContext() {
        return facesContext;
    }

    @Override
    protected Sucursal createNewRegistro() {
        return new Sucursal();
    }
}