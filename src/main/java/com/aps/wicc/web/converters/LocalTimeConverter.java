package com.aps.wicc.web.converters;

import com.aps.wicc.web.*;
import javax.faces.context.*;
import javax.faces.component.*;
import org.joda.time.format.*;
import javax.faces.application.*;
import javax.faces.convert.*;
import org.joda.time.*;

@FacesConverter("localTimeConverter")
public class LocalTimeConverter implements Converter
{
    private static final String pattern = "HH:mm";
    private Messages messages;
    
    public Object getAsObject(final FacesContext context, final UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        if (value.matches("[01][0-9][0-5][0-9]|2[0-3][0-5][0-9]")) {
            value = String.valueOf(value.substring(0, 2)) + ":" + value.substring(2);
        }
        try {
            return DateTimeFormat.forPattern(pattern).parseLocalTime(value);
        }
        catch (IllegalArgumentException e) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, this.messages.timeNotValid(), this.messages.timeNotValid()));
        }
    }
    
    public String getAsString(final FacesContext context, final UIComponent component, final Object value) {
        return DateTimeFormat.forPattern(pattern).print((ReadablePartial)value);
    }
}
