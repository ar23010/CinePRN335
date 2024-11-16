package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;


import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoSalaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoSala;
import java.io.Serializable;


@Named
@ViewScoped
public class FrmTipoSala extends AbstractFormulario<TipoSala> implements Serializable {

   @Inject
   TipoSalaBean tsBean;


   @Inject
   FacesContext facesContext;


    @Override
    public Object getId(TipoSala Object) {
        return Object.getIdTipoSala();
    }

    @Override
    public AbstractDataPersistence<TipoSala> getDataBean() {
        return tsBean;
    }

    @Override
    public FacesContext facesContext() {
        return facesContext;
    }

    @Override
    public TipoSala createNewRegistro() {
        return new TipoSala();
    }

    @Override
    public String buscarIdPorRegistro(TipoSala entity) {
        return "";
    }

    @Override
    protected FacesContext getContext() {
        return null;
    }

    @Override
    public TipoSala buscarRegistroPorId(String id) {
        return null;
    }

    @Override
    public String getTituloDePagina() {
        return "Tipo de Sala";
    }


}
