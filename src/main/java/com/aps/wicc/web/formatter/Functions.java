package com.aps.wicc.web.formatter;

import com.aps.wicc.model.*;

public final class Functions
{   
    public static String format(final ServiceGroupAlteration serviceGroupAlteration) {
        final StringBuilder builder = new StringBuilder();
        builder.append(serviceGroupAlteration.getServiceGroup().getHeadcode());
        builder.append(": ");
        builder.append(serviceGroupAlteration.getServiceGroup().getDescription());
        if (serviceGroupAlteration.getDirection() != Direction.BOTH) {
            builder.append(" ").append((serviceGroupAlteration.getDirection() == Direction.UP) ? "up" : "down");
        }
        builder.append(" ").append("services").append(" ");
        builder.append(shortFormat(serviceGroupAlteration));
        return builder.toString();
    }
    
    public static String shortFormat(final ServiceGroupAlteration serviceGroupAlteration) {
        final StringBuilder builder = new StringBuilder();
        switch (serviceGroupAlteration.getAffect()) {
            case DELAYED:    builder.append(delay(serviceGroupAlteration));    break;
            case ALTERED:    builder.append(altered(serviceGroupAlteration));  break;
            case RESTORED:   builder.append(restored());                       break;
            case CANCELLED:  builder.append(cancelled());                      break;
            case UNAFFECTED: builder.append(unaffacted());                     break;            
        }
        if (serviceGroupAlteration.getEffectiveFrom() != null && !serviceGroupAlteration.getEffectiveFrom().isEmpty()) {
            builder.append(" ").append("effective").append(" ").append(serviceGroupAlteration.getEffectiveFrom());
        }
        return builder.toString();
    }
    
    private static String delay(final ServiceGroupAlteration serviceGroupAlteration) {
        final StringBuilder builder = new StringBuilder();
        builder.append("will be delayed");
        if (serviceGroupAlteration.getDelay() != null && !serviceGroupAlteration.getDelay().isEmpty()) {
            builder.append(" ").append(serviceGroupAlteration.getDelay());
        }
        return builder.toString();
    }
    
    private static String altered(final ServiceGroupAlteration serviceGroupAlteration) {
        return Util.formatList(CompressedAlteration.build(serviceGroupAlteration.getAlterations()), new ListFormatterAlterationImpl());
    }
    
    private static String cancelled() {
        return "are cancelled";
    }
    
    private static String unaffacted() {
        return "are unaffected";
    }
    
    private static String restored() {
        return "will be restored";
    }
    
    static class ListFormatterAlterationImpl implements ListFormatter<CompressedAlteration>
    {
                
        @Override
        public String toString(final CompressedAlteration item) {
            final StringBuilder builder = new StringBuilder();
            switch (item.getAlterationType()) {
                case NEWORIGIN: builder.append("will start at "); break;               
                case CALLADD:   builder.append("will call additionally at "); break;
                case NEWDEST:   builder.append("will terminate at "); break;
                case NOTCALL:   builder.append("will not call at "); break;
                case RUNVIA:    builder.append("will run via "); break;                
            }
            builder.append(Util.formatList(item.getLocations(), new ListFormatterLocationImpl()));
            return builder.toString();
        }
        
    }
    
    static class ListFormatterLocationImpl implements ListFormatter<String>
    {
        @Override
        public String toString(final String item) {
            return item.toString();
        }
    }
}
