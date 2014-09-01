package com.aps.wicc.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import org.omnifaces.converter.SelectItemsConverter;

import com.aps.wicc.model.ServiceGroup;

@FacesConverter("serviceGroupSelectItemsConverter")
public class ServiceGroupSelectItemsConverter extends SelectItemsConverter {

    public String getAsString(final FacesContext context, final UIComponent component, final Object value) {
        final Long id = (value instanceof ServiceGroup) ? ((ServiceGroup)value).getId() : null;
        return (id != null) ? String.valueOf(id) : null;
    }

}
