package com.aps.wicc.web.email;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

class ResourceBundleMapper {

    public static Map<String, String> create(String resourceBundle) {

        ResourceBundle bundle = ResourceBundle.getBundle(resourceBundle);

        Map<String, String> map = new HashMap<>();

        for (String key : Collections.list(bundle.getKeys())) {

            map.put(key, bundle.getString(key));
        }

        return map;
    }
}
