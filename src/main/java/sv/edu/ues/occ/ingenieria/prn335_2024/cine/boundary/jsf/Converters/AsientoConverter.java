package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf.Converters;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AsientoBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Asiento;


@FacesConverter(value = "asientoConverter")
public class AsientoConverter implements Converter<Asiento> {

    @Inject
    private AsientoBean asientoBean;

    @Override
    public Asiento getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            Long id = Long.valueOf(value);
            return asientoBean.find(id);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Asiento asiento) {
        if (asiento == null) {
            return "";
        }
        return asiento.getIdAsiento().toString();
    }
}
