package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.TabChangeEvent;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AsientoBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Asiento;

import java.io.Serializable;

@Named
@ViewScoped
public class FrmAsiento extends AbstractForm<Asiento> implements Serializable {

    @Inject
    AsientoBean asientoBean;

    @Inject
    FacesContext facesContext;

    @Inject
    FrmAsientoCaracteristica frmAsientoCaracteristica;


    public void cambiarTab(TabChangeEvent tce){
        if(tce.getTab().getTitle().equals("Tipos")){
            if(this.registro!=null && this.frmAsientoCaracteristica!=null){
                this.frmAsientoCaracteristica.setIdAsiento(this.registro.getIdAsiento());
            }
        }
    }

    @Override
    protected Object getId(Asiento Object) {
        return Object.getIdAsiento();
    }

    @Override
    protected AbstractDataPersistence<Asiento> getDataBean() {
        return asientoBean;
    }

    @Override
    protected FacesContext facesContext() {
        return facesContext;
    }

    @Override
    protected Asiento createNewRegistro() {
        return new Asiento();
    }


    public FrmAsientoCaracteristica getFrmAsientoCaracteristica() {
        return frmAsientoCaracteristica;
    }
}
