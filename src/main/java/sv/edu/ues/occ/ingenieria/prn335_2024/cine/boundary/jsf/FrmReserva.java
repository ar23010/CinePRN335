package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;
import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.*;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Pelicula;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Programacion;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Reserva;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoReserva;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ViewScoped
@ManagedBean
public class FrmReserva extends AbstractFormulario<Reserva> implements Serializable {

    @Inject
    ProgramacionBean programacionBean;
    @Inject
    ReservaBean reservaBean;

    @Inject
    TipoReservaBean trBean;

    @Inject
    FacesContext facesContext;

    @Inject
    FrmSalaCaracteristica frmSalaCaracteristica;




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
    private List<TipoReserva> tipoReservas;

    @Inject
    private PeliculaBean peliculaBean;
    private List<Pelicula> peliculas;
    private List<Programacion> programacionList;
    private Programacion programacionSeleccionada;
    private List<TipoReserva> tipoReservaList;
    private Long idReserva;


    @PostConstruct
    public void init() {
        tipoReservas = trbean.findRange(0,1000);
        peliculas = peliculaBean.findRange(0,1000);
        super.inicializar();
        try {
            this.programacionList = programacionBean.findAll(0,Integer.MAX_VALUE);
            this.tipoReservaList = trBean.findRange(0, Integer.MAX_VALUE);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al cargar", "Error al cargar los tipos"));
        }
    }




    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public List<TipoReserva> getTipoReservas() {
        return tipoReservas;
    }


    public String onFlowProcess (FlowEvent event) {
        String pasoAnterior = event.getOldStep();
        String pasoNuevo = event.getNewStep();

        FacesContext context =  FacesContext.getCurrentInstance();
        if ("fecha".equals(pasoAnterior) && "funcion".equals(pasoNuevo)) {
            if (this.getRegistro().getFechaReserva() == null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe seleccionar una fecha."));
                return pasoAnterior;
            }
        }

        if ("funcion".equals(pasoAnterior) && "asientos".equals(pasoNuevo)) {
            if (this.getRegistro().getIdProgramacion().getIdPelicula().getNombre() == null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe seleccionar una película."));
                return pasoAnterior; // No permitir avanzar al siguiente paso
            }
        }
        if ("asientos".equals(pasoAnterior) && "confirm".equals(pasoNuevo)) {
            // Validación: Asegurarse de que haya asientos seleccionados
            if (frmAsiento.getRegistro() == null || frmAsiento.getRegistro().equals("")) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe seleccionar al menos un asiento."));
                return pasoAnterior; // No permitir avanzar al siguiente paso
            }
        }

        return pasoNuevo;
    }





    @Inject
    FrmAsiento frmAsiento;

    public FrmAsiento getFrmAsiento() {return frmAsiento;}


    public Object getTiposReservaList() {
    return tipoReservas;}


    private int activeIndex = 0;

    public int getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(int activeIndex) {
        this.activeIndex = activeIndex;
    }

    public void siguientePaso() {
        activeIndex++;
    }

    public void anteriorPaso(){
        activeIndex--;
    }


    public String getProgramacionLabel(Programacion programacion) {
        if (programacion != null) {
            String nombrePelicula = programacion.getIdPelicula().getNombre();
            String sala = programacion.getIdSala().getNombre();
            String sucursal= programacion.getIdSala().getIdSucursal().getNombre();
            OffsetDateTime desde = programacion.getDesde();
            OffsetDateTime hasta = programacion.getHasta();

            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            String formattedDesde = desde.format(timeFormatter);
            String formattedHasta = hasta.format(timeFormatter);

            return nombrePelicula + "," + sala + "-" + sucursal+"("+formattedDesde+"-"+formattedHasta+")";
        }
        return "";
    }


    public List<Programacion> completeProgramacion(String query) {
        List<Programacion> allProgramaciones = getProgramacionList();
        List<Programacion> filteredProgramaciones = new ArrayList<>();

        for (Programacion programacion : allProgramaciones) {
            if (programacion != null && programacion.getIdPelicula() != null &&
                    programacion.getIdPelicula().getNombre() != null &&
                    programacion.getIdPelicula().getNombre().toLowerCase().contains(query.toLowerCase())) {
                filteredProgramaciones.add(programacion);
            }
        }
        return filteredProgramaciones;
    }


    public List<Programacion> getProgramacionList() {

        return programacionList;
    }

    public void setProgramacionList(List<Programacion> programacionList) {
        this.programacionList = programacionList;
    }







    public Object getTipoReservaList() {
    return tipoReservaList;}

    public Integer getIdTipoReservaSeleccionado() {
        if (this.registro != null && this.registro.getIdTipoReserva() != null) {
            return this.registro.getIdTipoReserva().getIdTipoReserva();
        }
        return null;
    }

    public void setIdTipoReservaSeleccionado(final Integer idTipoReserva) {
        if (this.registro != null && this.tipoReservaList != null && !this.tipoReservaList.isEmpty()) {
            this.registro.setIdTipoReserva(this.tipoReservaList.stream().filter(r -> r.getIdTipoReserva().equals(idTipoReserva)).findFirst().orElse(null));
        }
    }

    public Programacion getProgramacionSeleccionada() {
        return programacionSeleccionada;
    }

    public void setProgramacionSeleccionada(Programacion programacionSeleccionada) {
        this.programacionSeleccionada = programacionSeleccionada;
    }

    public void onProgramacionSelect(SelectEvent<Programacion> event) {
        this.programacionSeleccionada = event.getObject();
    }
}