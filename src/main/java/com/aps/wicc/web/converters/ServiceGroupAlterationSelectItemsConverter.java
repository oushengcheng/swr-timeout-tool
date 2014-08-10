package com.aps.wicc.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import org.omnifaces.converter.SelectItemsConverter;

import com.aps.wicc.model.ServiceGroupAlteration;

@FacesConverter("serviceGroupAlterationSelectItemsConverter")
public class ServiceGroupAlterationSelectItemsConverter extends SelectItemsConverter {

    public String getAsString(final FacesContext context, final UIComponent component, final Object value) {
        return (value instanceof ServiceGroupAlteration) ? ((ServiceGroupAlteration) value).getUuid() : null;
    }

}
