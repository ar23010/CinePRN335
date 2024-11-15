package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;
import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.TabChangeEvent;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf.AbstractForm;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.PeliculaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.ReservaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoReservaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Pelicula;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Reserva;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Asiento;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoReserva;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
@ManagedBean
public class FrmReserva extends AbstractForm<Reserva> implements Serializable {


    @Inject
    ReservaBean reservaBean;

    @Inject
    FacesContext facesContext;

    @Override
    protected Object getId(Reserva Object) {
        return Object.getIdReserva();
    }

    @Override
    protected AbstractDataPersistence<Reserva> getDataBean() {
        return reservaBean;
    }

    @Override
    protected FacesContext facesContext() {
        return facesContext;
    }

    @Override
    protected Reserva createNewRegistro() {
        return new Reserva();
    }


    @Override
    public String buscarIdPorRegistro(Reserva entity) {
        if (entity != null && entity.getIdReserva() != null) {
            return entity.getIdReserva().toString();
        }
        return null;
    }

    @Override
    public Reserva buscarRegistroPorId(String id) {
        if (id != null && this.modelo != null) {
            return this.modelo.getWrappedData().stream()
                    .filter(r -> r.getIdReserva().toString().equals(id))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    @Override
    public String getTituloDePagina() {
        return "Reserva";
    }


    @Inject
    TipoReservaBean trbean;
    private List<TipoReserva> tiposReserva;

    @Inject
    private PeliculaBean peliculaBean;

    private List<Pelicula> peliculas;
    private String peliculasTexto;

    @PostConstruct
    public void init() {
        tiposReserva = trbean.findRange(0,1000);
        peliculas = peliculaBean.findRange(0,1000);
        peliculasTexto = concatenarPeliculas(peliculas);
    }

    private String concatenarPeliculas(List<Pelicula> peliculas) {
        StringBuilder sb = new StringBuilder();
        for (Pelicula pelicula : peliculas) {
            sb.append(pelicula.getNombre()).append("\n"); // Añadir el nombre de cada película y un salto de línea
        }
        return sb.toString();
    }

    public List<TipoReserva> getTiposReserva() {
        return tiposReserva;
    }

    public String onflowProces (FlowEvent event) {
        String pasoAnterior = event.getOldStep();
        String pasoNuevo = event.getNewStep();

        if ("Fecha".equals(pasoAnterior) && (registro.getFechaReserva() == null || registro.getIdTipoReserva() == null)) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar una fecha y un tipo de reserva", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return pasoAnterior; // No avanzar
        }
        return pasoNuevo;
    }





    @Inject
    FrmAsiento frmAsiento;

    public FrmAsiento getFrmAsiento() {return frmAsiento;}
}