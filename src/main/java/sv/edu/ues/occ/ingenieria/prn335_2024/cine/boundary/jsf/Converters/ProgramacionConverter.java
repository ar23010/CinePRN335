package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf.Converters;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.ProgramacionBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Programacion;

@Named
@ApplicationScoped
@FacesConverter(value = "programacionConverter", managed = true)
public class ProgramacionConverter implements Converter<Programacion> {

    @Inject
    private ProgramacionBean pBean;
    

       @Override
      public Programacion getAsObject(FacesContext context, UIComponent component, String value) {
           if (value != null && value.trim().length() > 0) {
               try {
                   Long id = Long.parseLong(value);
                   return pBean.getProgramacionesAsMap().get(id);
               } catch (NumberFormatException e) {
                   throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al conversion", "Error en el ID de programacion"));
               }
           } else {
               return null;
           }
       }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Programacion value) {
        if (value != null) {
            return String.valueOf(value.getIdProgramacion());
        } else {
            return null;
        }
    }
}