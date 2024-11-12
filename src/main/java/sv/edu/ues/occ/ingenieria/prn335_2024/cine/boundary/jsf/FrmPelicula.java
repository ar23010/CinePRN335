package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;


import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.TabChangeEvent;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.PeliculaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Pelicula;

import java.io.Serializable;

@Named
@ViewScoped
public class FrmPelicula extends AbstractForm<Pelicula> implements Serializable {

    @Inject
    PeliculaBean peliBean;

    @Inject
    FacesContext facesContext;

    @Inject
    FrmPeliculaCaracteristica frmPeliculaCaracteristica;


    public void cambiarTab(TabChangeEvent tce){
            if(tce.getTab().getTitle().equals("Tipos")){
                if(this.registro!=null && this.frmPeliculaCaracteristica!=null){
                    this.frmPeliculaCaracteristica.setIdPelicula(this.registro.getIdPelicula());
                }
            }
    }


    @Override
    protected Object getId(Pelicula Object) {
        return Object.getIdPelicula();
    }

    @Override
    protected AbstractDataPersistence<Pelicula> getDataBean() {
        return peliBean;
    }

    @Override
    protected FacesContext facesContext() {
        return facesContext;
    }

    @Override
    protected Pelicula createNewRegistro() {
        return new Pelicula();
    }

    @Override
    public String buscarIdPorRegistro(Pelicula entity) {
        if (entity != null && entity.getIdPelicula() != null) {
            return entity.getIdPelicula().toString();
        }
        return null;
    }

    @Override
    public Pelicula buscarRegistroPorId(String id) {
        if (id != null && this.modelo != null) {
            return this.modelo.getWrappedData().stream()
                    .filter(r -> r.getIdPelicula().toString().equals(id))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    @Override
    public String getTituloDePagina() {
        return "Pelicula";
    }


    public FrmPeliculaCaracteristica getFrmPeliculaCaracteristica() {
        return frmPeliculaCaracteristica;
    }



}
