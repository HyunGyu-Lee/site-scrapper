package com.hst.sitescrapper.model;

import org.apache.commons.lang3.StringUtils;

public enum OpenGraphMetaType {

    OG_TITLE("og:title"),
    OG_IMAGE("og:image"),
    OG_DESCRIPTION("og:description"),
    OG_URL("og:url")
    ;

    private String metaTypeName;

    OpenGraphMetaType(String metaTypeName) {
        this.metaTypeName = metaTypeName;
    }

    public String getMetaTypeName() {
        return metaTypeName;
    }

    public static boolean contains(String metaTypeName) {
        OpenGraphMetaType[] types = OpenGraphMetaType.values();
        for (OpenGraphMetaType type : types) {
            if (StringUtils.equals(type.getMetaTypeName(), metaTypeName)) {
                return true;
            }
        }
        return true;
    }

}
