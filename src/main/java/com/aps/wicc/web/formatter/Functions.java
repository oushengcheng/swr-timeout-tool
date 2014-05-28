package com.aps.wicc.web.formatter;

import com.aps.wicc.model.*;

public final class Functions
{   
    public static String format(final ServiceGroupAlteration serviceGroupAlteration) {
        
    	StringBuilder builder = new StringBuilder();
        
    	if (serviceGroupAlteration.getServiceGroup().getHeadcode().length() > 0) {
    	
    		builder.append(serviceGroupAlteration.getServiceGroup().getHeadcode());
            builder.append(": ");
            
    	}
    	
        if (serviceGroupAlteration.getServiceGroup().getUnidirectional() || serviceGroupAlteration.getDirection() == Direction.BOTH) {
        	
        	builder.append(serviceGroupAlteration.getServiceGroup().getBothDescription());
        	
        } else if (serviceGroupAlteration.getDirection() == Direction.FORWARD) {
        	
        	builder.append(serviceGroupAlteration.getServiceGroup().getForwardDescription());
        	
        } else {
        	
        	builder.append(serviceGroupAlteration.getServiceGroup().getReverseDescription());
        	
        }
        
        builder.append(" ").append("services").append(" ");
        builder.append(shortFormat(serviceGroupAlteration));
        return builder.toString();
    }
    
    public static String shortFormat(final ServiceGroupAlteration serviceGroupAlteration) {
        final StringBuilder builder = new StringBuilder();
        switch (serviceGroupAlteration.getAffect()) {
            case DELAYED:      builder.append(delay(serviceGroupAlteration));        break;
            case ALTERED:      builder.append(altered(serviceGroupAlteration));      break;
            case PARTRESTORED: builder.append(partrestored(serviceGroupAlteration)); break;
            case FULLRESTORED: builder.append(fullrestored());                       break;
            case CANCELLED:    builder.append(cancelled());                          break;
            case UNAFFECTED:   builder.append(unaffacted());                         break;            
        }
        if (serviceGroupAlteration.getEffectiveFrom() != null && !serviceGroupAlteration.getEffectiveFrom().isEmpty()) {
            builder.append(" ").append("effective").append(" ").append(serviceGroupAlteration.getEffectiveFrom());
        }
        return builder.toString();
    }
    
    private static String delay(final ServiceGroupAlteration serviceGroupAlteration) {
        final StringBuilder builder = new StringBuilder();
        builder.append("are delayed");
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
    
    private static String partrestored(ServiceGroupAlteration serviceGroupAlteration) {
        return "are restored but " + altered(serviceGroupAlteration);
    }
    
    private static String fullrestored() {
        return "are restored and will run as booked";
    }
    
    static class ListFormatterAlterationImpl implements ListFormatter<CompressedAlteration>
    {
                
        @Override
        public String toString(final CompressedAlteration item) {
            final StringBuilder builder = new StringBuilder();
            switch (item.getAlterationType()) {
                case NEWORIGIN:   builder.append("will start at ");                 break;               
                case CALLADD:     builder.append("will call additionally at ");     break;
                case NEWDEST:     builder.append("will terminate at ");             break;
                case NOTCALL:     builder.append("will not call at ");              break;
                case RUNVIA:      builder.append("will run via ");                  break;                
                case STARTFINISH: builder.append("will start and terminate at ");   break;
                case FINISHSTART: builder.append("will terminate and start at ");   break;
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
    
    public static String choice(Integer value, Integer lower, Integer upper, String lowerString, String middleString, String upperString) {
    	
    	if (value.compareTo(lower) < 0 ) {
    		
    		return lowerString;
    		
    	} else if (value.compareTo(upper) >= 0) {
    		
    		return upperString;
    		
    	} else {
    		
    		return middleString;
    		
    	}
    	    	
    }
}
