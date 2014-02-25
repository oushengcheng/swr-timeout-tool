package com.aps.wicc.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.joda.time.DateTimeZone;
import org.joda.time.ReadableInstant;
import org.joda.time.format.DateTimeFormat;

@FacesConverter("dateTimeConverter")
public class DateTimeConverter implements Converter
{
    private static final String defaultPattern = "HH:mm EEE d MMM yyyy";
    private DateTimeZone dateTimeZone;
    
    public Object getAsObject(final FacesContext context, final UIComponent component, final String value) {
        String pattern = (String) component.getAttributes().get("pattern");
        return DateTimeFormat.forPattern((pattern != null) ? pattern : defaultPattern).withZone(this.dateTimeZone).parseDateTime(value);
    }
    
    public String getAsString(final FacesContext context, final UIComponent component, final Object value) {
        String pattern = (String) component.getAttributes().get("pattern");
        return DateTimeFormat.forPattern((pattern != null) ? pattern : defaultPattern).withZone(this.dateTimeZone).print((ReadableInstant)value);
    }
}
