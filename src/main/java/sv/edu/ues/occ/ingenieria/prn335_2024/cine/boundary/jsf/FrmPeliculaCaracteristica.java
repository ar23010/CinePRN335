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
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.PeliculaCaracteristicaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoPeliculaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Pelicula;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.PeliculaCaracteristica;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoPelicula;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Named
@Dependent
public class FrmPeliculaCaracteristica extends AbstractFormulario<PeliculaCaracteristica> implements Serializable {

    @Inject
    PeliculaCaracteristicaBean pcBean;

    @Inject
    FacesContext facesContext;

    @Inject
    TipoPeliculaBean tpBean;


    List<TipoPelicula> tipoPeliculaList;

    Long idPelicula;

    @PostConstruct
    @Override
    public void inicializar(){
        super.inicializar();
        try{
            this.tipoPeliculaList = tpBean.findRange(0, Integer.MAX_VALUE);
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            enviarMensaje("Error al cargar los tipos", "Error al cargar" , FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public List<PeliculaCaracteristica> cargarDatos(int firstResult, int maxResults){
        try{
            if(this.idPelicula != null && pcBean!=null){
                return pcBean.findByIdPelicula(this.idPelicula,firstResult,maxResults);
            }
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return List.of();
    }


    @Override
    public int contarRegistros(){
        try{
            if(idPelicula != null && pcBean!=null){
                return pcBean.countPelicula(this.idPelicula);
            }
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return 0;
    }

    @Override
    protected Object getId(PeliculaCaracteristica Object) {
        return Object.getIdPeliculaCaracteristica();
    }

    @Override
    protected AbstractDataPersistence<PeliculaCaracteristica> getDataBean() {
        return pcBean;
    }

    @Override
    protected FacesContext facesContext() {
        return facesContext;
    }

    @Override
    protected PeliculaCaracteristica createNewRegistro() {
        PeliculaCaracteristica pc = new PeliculaCaracteristica();
        if(idPelicula!=null){
            pc.setIdPelicula(new Pelicula(idPelicula));
        }
        if(tipoPeliculaList!=null && tipoPeliculaList.isEmpty()){
            pc.setIdTipoPelicula(tipoPeliculaList.getFirst());
        }
        return pc;
    }

    @Override
    public String buscarIdPorRegistro(PeliculaCaracteristica entity) {
        if (entity != null && entity.getIdPeliculaCaracteristica() != null) {
            return entity.getIdPeliculaCaracteristica().toString();
        }
        return null;
    }

    @Override
    public PeliculaCaracteristica buscarRegistroPorId(String id) {
        if (id != null && this.modelo != null) {
            return this.modelo.getWrappedData().stream()
                    .filter(r -> r.getIdPeliculaCaracteristica().toString().equals(id))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    @Override
    public String getTituloDePagina() {
        return "Pelicula Caracteristica";
    }


    public Long getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Long idPelicula) {
        this.idPelicula = idPelicula;
    }


    public List<TipoPelicula> getTipoPeliculaList() {
        return tipoPeliculaList;
    }

    public Integer getIdTipoPeliculaSeleccionado() {
        if(this.registro!=null && this.registro.getIdTipoPelicula()!=null){
            return this.registro.getIdTipoPelicula().getIdTipoPelicula();
        }
        return null;
    }


    public void setIdTipoPeliculaSeleccionado(final Integer idTipoPelicula) {
        if(this.registro!=null && this.tipoPeliculaList!=null && !this.tipoPeliculaList.isEmpty()){
            this.registro.setIdTipoPelicula(this.tipoPeliculaList.stream().filter(r->r.getIdTipoPelicula().equals(idTipoPelicula)).findFirst().orElse(null));
        }
    }


    public void validarValor(FacesContext fc, UIComponent componente, Object valor){
        UIInput input = (UIInput) componente;

        if(registro!= null && this.registro.getIdTipoPelicula()!=null){
                String nuevo= valor.toString();
                Pattern patron=Pattern.compile(this.registro.getIdTipoPelicula().getExpresionRegular());
                Matcher validador= patron.matcher(nuevo);
                if(validador.find()){
                    input.setValid(true);
                    return;
                }
        }
                input.setValid(false);
                FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Valor incorrecto", "Valor ingresado inválidoS."));

    }
}
