package com.aps.wicc.web.converters;

import org.ocpsoft.prettytime.jsf.*;
import javax.inject.*;
import javax.faces.convert.*;
import javax.faces.context.*;
import javax.faces.component.*;
import org.joda.time.*;
import java.util.*;

@Named
@FacesConverter("prettyJodaTimeConverter")
public class PrettyJodaTimeConverter extends PrettyTimeConverter
{
    private static final long serialVersionUID = -7092244860259925929L;
    
    public String getAsString(final FacesContext context, final UIComponent comp, final Object value) {
        if (value instanceof DateTime) {
            final Date date = ((DateTime)value).toDate();
            return super.getAsString(context, comp, (Object)date);
        }
        return super.getAsString(context, comp, value);
    }
}
