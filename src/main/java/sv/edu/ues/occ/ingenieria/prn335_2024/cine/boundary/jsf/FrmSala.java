package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;


import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.SalaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class FrmSala extends AbstractForm<Sala> implements Serializable {
    private ScheduleModel eventModel;
    private ScheduleEvent<?> event = new DefaultScheduleEvent();
    private String serverTimeZone = ZoneId.systemDefault().toString();
    private String nomPelicula;

    @Inject
    SalaBean salaBean;

    @Inject
    FacesContext facesContext;

    @Inject
    FrmSalaCaracteristica frmSalaCaracteristica;

    @Inject
    FrmAsiento frmAsiento;

    @Inject
    FrmProgramacion frmProgramacion;





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


    public FrmAsiento getFrmAsiento() {return frmAsiento;}

    public FrmProgramacion getFrmProgramacion() {
        return frmProgramacion;
    }







}
