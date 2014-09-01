package com.aps.wicc.web.backing.error;

import javax.inject.*;
import javax.enterprise.context.*;
import com.aps.wicc.web.*;

@Named
@RequestScoped
public class ServerErrorBacking
{
    public Class<?> returnToIncidentList() {
        return Pages.Editsummary.class;
    }
}
