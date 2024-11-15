package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.Dependent;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AsientoCaracteristicaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoAsientoBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Named
@Dependent
public class FrmAsientoCaracteristica extends AbstractFormulario<AsientoCaracteristica> {


    @Inject
    AsientoCaracteristicaBean acBean;

    @Inject
    FacesContext facesContext;

    @Inject
    TipoAsientoBean taBean;

    List<TipoAsiento> tipoAsientoList;
    List<AsientoCaracteristica> AsientoCaracteristicaList;
    Long idAsiento;


    @PostConstruct
    @Override
    public void inicializar(){
        super.inicializar();
        try{
            this.tipoAsientoList = taBean.findRange(0, Integer.MAX_VALUE);
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            enviarMensaje("Error al cargar los tipos", "Error al cargar" , FacesMessage.SEVERITY_ERROR);
        }
    }
    public List<AsientoCaracteristica> cargarDatos(int firstResult, int maxResults){
        try{
            if(this.idAsiento != null && acBean!=null){
                return acBean.findByIdAsiento(this.idAsiento,firstResult,maxResults);
            }
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return List.of();
    }

    @Override
    protected Object getId(AsientoCaracteristica Object) {
        return Object.getIdAsientoCaracteristica();
    }

    @Override
    protected AbstractDataPersistence<AsientoCaracteristica> getDataBean() {
        return acBean;
    }

    @Override
    protected FacesContext facesContext() {
        return facesContext;
    }

    @Override
    protected AsientoCaracteristica createNewRegistro() {
        AsientoCaracteristica ac = new AsientoCaracteristica();
        if(idAsiento!=null){
            ac.setIdAsiento(new Asiento(idAsiento));
        }
        if(tipoAsientoList!=null && tipoAsientoList.isEmpty()){
            ac.setIdTipoAsiento(tipoAsientoList.getFirst());
        }
        return ac;
    }

    @Override
    public String buscarIdPorRegistro(AsientoCaracteristica entity) {
        return "";
    }

    @Override
    public AsientoCaracteristica buscarRegistroPorId(String id) {
        return null;
    }

    @Override
    public String getTituloDePagina() {
        return "Asiento Caracteristica";
    }

    public Long getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(Long idAsiento) {
        this.idAsiento = idAsiento;
    }

    public TipoAsientoBean getTaBean() {
        return taBean;
    }

    public List<TipoAsiento> getTipoAsientoList() {
        return tipoAsientoList;
    }

    public Integer getIdTipoAsientoSeleccionado() {
        if(this.registro!=null && this.registro.getIdTipoAsiento()!=null){
            return this.registro.getIdTipoAsiento().getIdTipoAsiento();
        }
        return null;
    }


    public void setIdTipoAsientoSeleccionado(final Integer idTipoAsiento) {
        if(this.registro!=null && this.tipoAsientoList!=null && !this.tipoAsientoList.isEmpty()){
            this.registro.setIdTipoAsiento(this.tipoAsientoList.stream().filter(r->r.getIdTipoAsiento().equals(idTipoAsiento)).findFirst().orElse(null));
        }
    }

    public Long getIdAsientoSeleccionado() {
        if(this.registro!=null && this.registro.getIdAsiento()!=null){
            return this.registro.getIdAsiento().getIdAsiento();
        }
        return null;
    }


    public void validarValor(FacesContext fc, UIComponent componente, Object valor){
        UIInput input = (UIInput) componente;

        if(registro!= null && this.registro.getIdTipoAsiento()!=null){
            String nuevo= valor.toString();
            Pattern patron=Pattern.compile(this.registro.getIdTipoAsiento().getExpresionRegular());
            Matcher validador= patron.matcher(nuevo);
            if(validador.find()){
                input.setValid(true);
                return;
            }
        }
        input.setValid(false);
    }

    public Object getAsientoCaracteristicaList() {return AsientoCaracteristicaList;}
}

