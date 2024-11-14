package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Schedule;
import jakarta.enterprise.context.Dependent;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleModel;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.PeliculaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.ProgramacionBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.SalaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Pelicula;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.PeliculaCaracteristica;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Programacion;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.SalaCaracteristica;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Named
@Dependent
public class FrmProgramacion extends AbstractForm<Programacion> implements Serializable {

    @Inject
    ProgramacionBean prBean;

    @Inject
    FacesContext facesContext;

    @Inject
     PeliculaBean pBean;

    @Inject
    ScheduleJava8View scheduleJava8View;

    @Inject
    FrmPelicula frmPelicula;


    private ScheduleModel eventModel;

    List<Pelicula> peliculaList;




    @Override
    protected Object getId(Programacion Object) {
        return Object.getIdProgramacion();
    }

    @Override
    protected AbstractDataPersistence<Programacion> getDataBean() {
        return prBean;
    }

    @Override
    protected FacesContext facesContext() {
        return facesContext;
    }

    @Override
    protected Programacion createNewRegistro() {
        return new Programacion();
    }

    @Override
    public String buscarIdPorRegistro(Programacion entity) {
        if (entity != null && entity.getIdProgramacion() != null) {
            return entity.getIdProgramacion().toString();
        }
        return null;
    }

    @Override
    public Programacion buscarRegistroPorId(String id) {
        if (id != null && this.modelo != null) {
            return this.modelo.getWrappedData().stream()
                    .filter(r -> r.getIdProgramacion().toString().equals(id))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    @Override
    public String getTituloDePagina() {
        return "Programación";
    }


    @Override
    public List<Programacion> cargarDatos(int firstResult, int maxResults){
        try{
            if(prBean!=null){
                return prBean.findAll(firstResult,maxResults);
            }
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return List.of();
    }


    public FrmPelicula getFrmPelicula() {
        return frmPelicula;
    }

    public ScheduleJava8View getScheduleJava8View() {
        return scheduleJava8View;
    }




}
