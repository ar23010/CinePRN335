package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.*;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.*;

import java.io.Serializable;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class FrmReserva extends AbstractFormulario<Reserva> implements Serializable {

    @Inject
    ReservaBean rBean;

    @Inject
    FacesContext facesContext;

    @Inject
    FrmProgramacion frmProgramacion;

    @Inject
    ProgramacionBean prBean;

    @Inject
    TipoReservaBean trBean;

    @Inject
    AsientoBean aBean;

    @Inject
    ReservaDetalleBean rdBean;

    Asiento seleccionadoReserva;
    Asiento seleccionadoOcupado;

    Integer IdTipoReservaSeleccionado;

    private List<TipoReserva> tipoReservaList;

    List<Asiento> asientosDisponiblesList = new ArrayList<>();
    List<Asiento> asientoOcupadosList = new ArrayList<>();

    private Date fechaSeleccionada;

    Programacion programacionSeleccionada;


    @PostConstruct
    public void inicializar() {
        super.inicializar();
        this.tipoReservaList = trBean.findRange(0, Integer.MAX_VALUE);
    }

    public String formatearLabel(Programacion programacion) {
        if (programacion != null) {
            String formatearDesde = formatearHora(programacion.getDesde());
            String formatearHasta = formatearHora(programacion.getHasta());
            return String.format("%s, %s - %s (%s-%s)",
                    programacion.getIdPelicula().getNombre(),
                    programacion.getIdSala().getNombre(),
                    programacion.getIdSala().getIdSucursal().getNombre(),
                    formatearDesde,
                    formatearHasta
            );
        }
        return "";
    }

    public List<Programacion> completarTexto(String query) {
        String queryLowerCase = query.toLowerCase();
        try {
            LocalDate fechaSeleccionadaLocalDate = fechaSeleccionada.toInstant()
                    .atZone(ZoneId.of("UTC-6"))
                    .toLocalDate();
            LocalDateTime DiaInicio = fechaSeleccionadaLocalDate.atStartOfDay();
            OffsetDateTime desde = DiaInicio.atOffset(ZoneOffset.ofHours(-6));
            LocalDateTime DiaFin = fechaSeleccionadaLocalDate.atTime(23, 59, 59);
            OffsetDateTime hasta = DiaFin.atOffset(ZoneOffset.ofHours(-6));
            List<Programacion> programaciones = prBean.findByDate(desde, hasta);
            return programaciones.stream()
                    .filter(p -> p.getIdPelicula().getNombre().toLowerCase().startsWith(queryLowerCase)
                            || p.getIdSala().getNombre().toLowerCase().contains(queryLowerCase)
                            || p.getDesde().toString().toLowerCase().contains(queryLowerCase)
                            || p.getHasta().toString().toLowerCase().contains(queryLowerCase)
                    )
                    .collect(Collectors.toList());
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error en cargar las programaciones", e);
        }
        return List.of();
    }

    public String formatearHora(OffsetDateTime dateTime) {
        if (dateTime != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            return dateTime.format(formatter);
        }
        return "";
    }

    public String onFlowProcess(FlowEvent event) {
        String currentStep = event.getOldStep();
        String nextStep = event.getNewStep();
        if (nextStep.equals("asientos")) {
            if (this.programacionSeleccionada != null) {
                OffsetDateTime fechaHoraActual = OffsetDateTime.now(ZoneOffset.of("-06:00"));
                if (programacionSeleccionada.getDesde().isBefore(fechaHoraActual)) {
                    return currentStep;
                } else if (asientosDisponiblesList.isEmpty() && asientoOcupadosList.isEmpty()) {
                    cargarAsientos(this.programacionSeleccionada.getIdProgramacion());
                }
            }
        }
        if (nextStep.equals("fecha")) {
            this.programacionSeleccionada = null;
            this.seleccionadoReserva = null;
            this.seleccionadoOcupado = null;
        }
        if (nextStep.equals("Confirmar")) {
            if (asientoOcupadosList.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Advertencia", "No puede avanzar porque hay asientos ocupados."));
                return currentStep;
            }
        }
        if (nextStep.equals("Funcion")) {
            asientosDisponiblesList.clear();
            asientoOcupadosList.clear();
            OffsetDateTime fechaHoraActual = OffsetDateTime.now(ZoneOffset.of("-06:00"));
            Date fechaActual = Date.from(fechaHoraActual.toLocalDate().atStartOfDay(ZoneOffset.of("-06:00")).toInstant());
            if (this.fechaSeleccionada.before(fechaActual)) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Advertencia", "Debe seleccionar una fecha actual o posterior."));
                return currentStep;
            }
        }
        return nextStep;
    }


    public void cargarAsientos(Long idProgramacion) {
        if (idProgramacion != null) {
            this.asientosDisponiblesList = aBean.findDisponiblesByIdProgramacion(idProgramacion);
            if (this.asientosDisponiblesList.isEmpty()) {
                enviarMensaje("En esta programacion ya no existen asientos disponibles", "Error", FacesMessage.SEVERITY_ERROR);
            }
        } else {
            enviarMensaje("Debe seleccionar una programación", "Error de programación", FacesMessage.SEVERITY_ERROR);
        }
    }


    public void btnReservarHandler(ActionEvent event) {
        if (asientosDisponiblesList != null && !asientosDisponiblesList.isEmpty()) {
            asientoOcupadosList.add(seleccionadoReserva);
            asientosDisponiblesList.remove(seleccionadoReserva);
            seleccionadoReserva = null;
        }
    }


    public void btnEliminarAsientoReserva(ActionEvent event) {
        if (getAsientoOcupadosList() != null && !asientoOcupadosList.isEmpty()) {
            asientosDisponiblesList.add(seleccionadoOcupado);
            asientoOcupadosList.remove(seleccionadoOcupado);
            seleccionadoOcupado = null;
        }
    }

    public void btnConfirmarReserva() {
        try {
            Reserva reserva = new Reserva();
            reserva.setIdTipoReserva(
                    this.tipoReservaList.stream()
                            .filter(r -> r.getIdTipoReserva().equals(IdTipoReservaSeleccionado))
                            .findFirst()
                            .orElse(null)
            );
            reserva.setIdProgramacion(programacionSeleccionada);
            OffsetDateTime fechaReservaOffsetDateTime = fechaSeleccionada.toInstant()
                    .atOffset(ZoneOffset.UTC);
            reserva.setFechaReserva(fechaReservaOffsetDateTime);
            reserva.setEstado("CREADO");
            rBean.create(reserva);
            ReservaDetalle reservaDetalle = new ReservaDetalle();
            reservaDetalle.setIdReserva(reserva);
            reservaDetalle.setEstado("CREADO");
            for (Asiento asiento : asientoOcupadosList) {
                try {
                    reservaDetalle.setIdAsiento(asiento);
                    rdBean.create(reservaDetalle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            reserva = null;
            reservaDetalle = null;
            enviarMensaje("Éxito", "Reserva creada con éxito", FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().getExternalContext().redirect("Reserva.jsf");
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error al crear la Reserva", e);
        }
    }

    @Override
    public String getTituloDePagina() {
        return "Reserva";
    }

    @Override
    protected Object getId(Reserva reserva) {
        return reserva.getIdReserva();
    }

    @Override
    protected Reserva createNewRegistro() {
        return new Reserva();
    }

    @Override
    protected AbstractDataPersistence<Reserva> getDataBean() {
        return rBean;
    }

    @Override
    protected FacesContext facesContext() {
        return null;
    }

    @Override
    protected FacesContext getContext() {
        return facesContext;
    }

    @Override
    public Reserva buscarRegistroPorId(String id) {
        return null;
    }

    @Override
    public String buscarIdPorRegistro(Reserva entity) {
        return "";
    }


    public Date getFechaSeleccionada() {
        return fechaSeleccionada;
    }

    public void setFechaSeleccionada(Date fechaSeleccionada) {
        this.fechaSeleccionada = fechaSeleccionada;
    }

    public Programacion getProgramacionSeleccionada() {
        return programacionSeleccionada;
    }

    public void setProgramacionSeleccionada(Programacion programacionSeleccionada) {
        this.programacionSeleccionada = programacionSeleccionada;
        this.estado = ESTADO_CRUD.MODIFICAR;
    }

    public FrmProgramacion getFrmProgramacion() {
        return frmProgramacion;
    }

    public void setFrmProgramacion(FrmProgramacion frmProgramacion) {
        this.frmProgramacion = frmProgramacion;
    }

    public List<TipoReserva> getTipoReservaList() {
        return tipoReservaList;
    }

    public List<Asiento> getAsientosDisponiblesList() {
        return asientosDisponiblesList;
    }

    public void setAsientosDisponiblesList(List<Asiento> asientosDisponiblesList) {
        this.asientosDisponiblesList = asientosDisponiblesList;
    }

    public List<Asiento> getAsientoOcupadosList() {
        return asientoOcupadosList;
    }

    public void setAsientoOcupadosList(List<Asiento> asientoOcupadosList) {
        this.asientoOcupadosList = asientoOcupadosList;
    }

    public Asiento getSeleccionadoReserva() {
        return seleccionadoReserva;
    }

    public void setSeleccionadoReserva(Asiento seleccionadoReserva) {
        this.seleccionadoReserva = seleccionadoReserva;
    }

    public Asiento getSeleccionadoOcupado() {
        return seleccionadoOcupado;
    }

    public void setSeleccionadoOcupado(Asiento seleccionadoOcupado) {
        this.seleccionadoOcupado = seleccionadoOcupado;
    }

    public void setTipoReservaList(List<TipoReserva> tipoReservaList) {
        this.tipoReservaList = tipoReservaList;
    }

    public Integer getIdTipoReservaSeleccionado() {
        return IdTipoReservaSeleccionado;
    }

    public void setIdTipoReservaSeleccionado(Integer idTipoReservaSeleccionado) {
        this.IdTipoReservaSeleccionado = idTipoReservaSeleccionado;
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

    private List<Programacion> getProgramacionList() {
    return getProgramacionList();}

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
        return "Programacion no disponible";
    }
    public void onProgramacionSelect(SelectEvent<Programacion> event) {
        this.programacionSeleccionada = event.getObject();
    }
}