package com.aps.wicc.ejb.initialisation;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class GlobalSetUpBean implements Initialisable {

    @Override
    public void init() {

        ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);

    }

}
