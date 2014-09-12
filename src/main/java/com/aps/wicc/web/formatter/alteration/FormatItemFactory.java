package com.aps.wicc.web.formatter.alteration;

import com.aps.wicc.model.Alteration;

public class FormatItemFactory {

    public static FormatItem create(Alteration alteration) {

        FormatItem formatItem = null;

        switch (alteration.getAlterationType()) {
        case NEWORIGIN:            formatItem = new NewOrigin(alteration);   break;
        case CALLADD:              formatItem = new CallAdd(alteration);     break;
        case NEWDEST:              formatItem = new NewDest(alteration);     break;
        case NOTCALL:              formatItem = new NotCall(alteration);     break;
        case RUNVIA:               formatItem = new RunVia(alteration);      break;
        case STARTFINISH:          formatItem = new StartFinish(alteration); break;
        case FINISHSTART:          formatItem = new FinishStart(alteration); break;
        case ALLSTATIONSFROM:      formatItem = new AllStationsFrom(alteration); break;
        case ALLSTATIONSTO:        formatItem = new AllStationsTo(alteration);   break;
        case REVERSEAT:            formatItem = new ReverseAt(alteration);       break;
        case RUNFASTFROM:          formatItem = new RunFastFrom(alteration);     break;
        case RUNFASTTO:            formatItem = new RunFastTo(alteration);       break;
        case THENASBOOKEDFROM:     formatItem = new ThenAsBookedFrom(alteration); break;
        case THENASBOOKEDTO:       formatItem = new ThenAsBookedTo(alteration);   break;
        default:
            throw new AssertionError();
        }

        return formatItem;

    }
}
