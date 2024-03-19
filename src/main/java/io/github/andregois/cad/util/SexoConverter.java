package io.github.andregois.cad.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import io.github.andregois.cad.enuns.SexoEnum;

@FacesConverter("sexoConverter")
public class SexoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return SexoEnum.fromCodigo(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof SexoEnum) {
            return ((SexoEnum) value).getCodigo();
        }
        throw new IllegalArgumentException("Tipo não suportado: " + value.getClass().getName());
    }
}
