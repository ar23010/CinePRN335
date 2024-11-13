package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;


import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.TabChangeEvent;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.SalaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Sala;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Asiento;
import java.io.Serializable;

@Named
@ViewScoped
public class FrmSala extends AbstractForm<Sala> implements Serializable {


    @Inject
    SalaBean salaBean;

    @Inject
    FacesContext facesContext;

    @Inject
    FrmSalaCaracteristica frmSalaCaracteristica;

    @Inject
    FrmAsiento frmAsiento;




    public void cambiarTab(TabChangeEvent tce){
        if(tce.getTab().getTitle().equals("Caracteristicas")){
            if(this.registro!=null && this.frmSalaCaracteristica!=null){
                this.frmSalaCaracteristica.setIdSala(this.registro.getIdSala());
            }
        }
    }



    @Override
    protected Object getId(Sala Object) {
        return Object.getIdSala();
    }

    @Override
    protected AbstractDataPersistence<Sala> getDataBean() {
        return salaBean;
    }

    @Override
    protected FacesContext facesContext() {
        return facesContext;
    }

    @Override
    protected Sala createNewRegistro() {
        return new Sala();
    }

    @Override
    public String buscarIdPorRegistro(Sala entity) {
        if (entity != null && entity.getIdSala() != null) {
            return entity.getIdSala().toString();
        }
        return null;
    }

    @Override
    public Sala buscarRegistroPorId(String id) {
        if (id != null && this.modelo != null) {
            return this.modelo.getWrappedData().stream()
                    .filter(r -> r.getIdSala().toString().equals(id))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    @Override
    public String getTituloDePagina() {
        return "Sala";
    }


    public FrmSalaCaracteristica getFrmSalaCaracteristica() {return frmSalaCaracteristica;}


    public Object getFrmAsiento() {return frmAsiento;}

}
