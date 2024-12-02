package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;


import jakarta.annotation.PostConstruct;
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
        DefaultSubMenu cine = DefaultSubMenu.builder()
                .label("Cine")
                .expanded(true)
                .build();
        DefaultMenuItem itemTipo1 = DefaultMenuItem.builder()
                .value("Asiento")
                .ajax(true)
                .command("#{frmMenu.navegar('TipoAsiento.jsf')}")
                .build();
        DefaultMenuItem itemTipo2 = DefaultMenuItem.builder()
                .value("Pago")
                .ajax(true)
                .command("#{frmMenu.navegar('TipoPago.jsf')}")
                .build();
        DefaultMenuItem itemTipo3 = DefaultMenuItem.builder()
                .value("Pelicula")
                .ajax(true)
                .command("#{frmMenu.navegar('TipoPelicula.jsf')}")
                .build();
        DefaultMenuItem itemTipo4 = DefaultMenuItem.builder()
                .value("Producto")
                .ajax(true)
                .command("#{frmMenu.navegar('TipoProducto.jsf')}")
                .build();
        DefaultMenuItem itemTipo5 = DefaultMenuItem.builder()
                .value("Reserva")
                .ajax(true)
                .command("#{frmMenu.navegar('TipoReserva.jsf')}")
                .build();
        DefaultMenuItem itemTipo6 = DefaultMenuItem.builder()
                .value("Sala")
                .ajax(true)
                .command("#{frmMenu.navegar('TipoSala.jsf')}")
                .build();
        DefaultMenuItem itemCine1 = DefaultMenuItem.builder()
                .value("Pelicula")
                .ajax(true)
                .command("#{frmMenu.navegar('Pelicula.jsf')}")
                .build();
        DefaultMenuItem itemCine2 = DefaultMenuItem.builder()
                .value("Sala")
                .ajax(true)
                .command("#{frmMenu.navegar('Sala.jsf')}")
                .build();
        DefaultMenuItem itemCine3 = DefaultMenuItem.builder()
                .value("Sucursal")
                .ajax(true)
                .command("#{frmMenu.navegar('Sucursal.jsf')}")
                .build();
        DefaultMenuItem itemCine4 = DefaultMenuItem.builder()
                .value("Reserva")
                .ajax(true)
                .command("#{frmMenu.navegar('Reserva.jsf')}")
                .build();
        tipos.getElements().add(itemTipo1);
        tipos.getElements().add(itemTipo2);
        tipos.getElements().add(itemTipo3);
        tipos.getElements().add(itemTipo4);
        tipos.getElements().add(itemTipo5);
        tipos.getElements().add(itemTipo6);
        cine.getElements().add(itemCine1);
        cine.getElements().add(itemCine2);
        cine.getElements().add(itemCine3);
        cine.getElements().add(itemCine4);

        model.getElements().add(tipos);
        model.getElements().add(cine);


    }


    public void navegar(String formulario) throws IOException {
        facesContext.getExternalContext().redirect(formulario);
    }


    public DefaultMenuModel getModel() {
        return model;
    }

}
