package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Default;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;

import java.io.IOException;
import java.io.Serializable;

@Named
@ViewScoped
public class FrmMenu implements Serializable {

    @Inject
    FacesContext facesContext;


    DefaultMenuModel model;

    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();
        DefaultSubMenu tipos = DefaultSubMenu.builder()
                .label("Tipos")
                .expanded(true)
                .build();
        DefaultMenuItem item = DefaultMenuItem.builder()
                .value("Asiento")
                .ajax(true)
                .command("#{frmMenu.navegar('TipoAsiento.jsf')}")
                .build();
        DefaultMenuItem item2 = DefaultMenuItem.builder()
                .value("Pago")
                .ajax(true)
                .command("#{frmMenu.navegar('TipoPago.jsf')}")
                .build();
        DefaultMenuItem item3 = DefaultMenuItem.builder()
                .value("Pelicula")
                .ajax(true)
                .command("#{frmMenu.navegar('TipoPelicula.jsf')}")
                .build();
        DefaultMenuItem item4 = DefaultMenuItem.builder()
                .value("Producto")
                .ajax(true)
                .command("#{frmMenu.navegar('TipoProducto.jsf')}")
                .build();
        DefaultMenuItem item5 = DefaultMenuItem.builder()
                .value("Reserva")
                .ajax(true)
                .command("#{frmMenu.navegar('TipoReserva.jsf')}")
                .build();
        DefaultMenuItem item6 = DefaultMenuItem.builder()
                .value("Sala")
                .ajax(true)
                .command("#{frmMenu.navegar('TipoSala.jsf')}")
                .build();
        tipos.getElements().add(item);
        tipos.getElements().add(item2);
        tipos.getElements().add(item3);
        tipos.getElements().add(item4);
        tipos.getElements().add(item5);
        tipos.getElements().add(item6);


        model.getElements().add(tipos);


    }


    public void navegar(String formulario) throws IOException {
        facesContext.getExternalContext().redirect(formulario);
    }


    public DefaultMenuModel getModel() {
        return model;
    }

}
