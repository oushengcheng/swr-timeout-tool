package com.aps.wicc.web.converters;

import org.omnifaces.converter.*;
import javax.faces.convert.*;
import javax.faces.context.*;
import javax.faces.component.*;
import com.aps.wicc.model.*;

@FacesConverter("serviceGroupSelectItemsConverter")
public class ServiceGroupSelectItemsConverter extends SelectItemsConverter
{
    public String getAsString(final FacesContext context, final UIComponent component, final Object value) {
        final Long id = (value instanceof ServiceGroup) ? ((ServiceGroup)value).getId() : null;
        return (id != null) ? String.valueOf(id) : null;
    }
}
