package com.aps.wicc.web.converters;

import org.omnifaces.converter.*;
import javax.faces.convert.*;
import javax.faces.context.*;
import javax.faces.component.*;
import com.aps.wicc.model.*;

@FacesConverter("serviceGroupAlterationSelectItemsConverter")
public class ServiceGroupAlterationSelectItemsConverter extends SelectItemsConverter
{
    public String getAsString(final FacesContext context, final UIComponent component, final Object value) {
        return (value instanceof ServiceGroupAlteration) ? ((ServiceGroupAlteration)value).getUuid() : null;
    }
}
