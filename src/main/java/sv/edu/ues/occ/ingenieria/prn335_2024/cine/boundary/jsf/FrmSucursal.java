package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.SucursalBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Sucursal;

import java.io.Serializable;


@Named
@ViewScoped
public class FrmSucursal extends AbstractFormulario<Sucursal> implements Serializable {

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

    @Override
    public String buscarIdPorRegistro(Sucursal entity) {
        return "";
    }

    @Override
    public Sucursal buscarRegistroPorId(String id) {
        return null;
    }

    @Override
    public String getTituloDePagina() {
        return "Sucursal";
    }
}