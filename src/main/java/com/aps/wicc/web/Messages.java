package com.aps.wicc.web;

import org.apache.deltaspike.core.api.message.*;

@MessageBundle
@MessageContextConfig(messageSource = { "messages" })
public interface Messages
{
    @MessageTemplate("{login_incorrect_credentials}")
    String incorrectCredentials();
    
    @MessageTemplate("{time_not_valid}")
    String timeNotValid();
    
    @MessageTemplate("{stale_data}")
    String staleData();
}
