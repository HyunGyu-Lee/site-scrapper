package com.hst.sitescrapper.type;

import org.apache.commons.lang3.StringUtils;

public enum MetadataType {

    /* todo 내가 정의한 Scrap 이 보유한 속성 (제목, 설명, 이미지 URL) 등을 Enum으로 정의하고
     * 각 속성이 있는 위치 (Fetch, 예를 들어 어느 태그의 어느 키값, 그걸 MetadataReader에서 보고 꺼낼 수 있도록 이곳에 모두 정의
     * 빡시게 설계해보자
     **/

    OG_TITLE("og:title"),
    OG_IMAGE("og:image"),
    OG_DESCRIPTION("og:description"),
    OG_URL("og:url")
    ;

    private String metaTypeName;

    MetadataType(String metaTypeName) {
        this.metaTypeName = metaTypeName;
    }

    public String getMetaTypeName() {
        return metaTypeName;
    }

    public static boolean contains(String metaTypeName) {
        MetadataType[] types = MetadataType.values();
        for (MetadataType type : types) {
            if (StringUtils.equals(type.getMetaTypeName(), metaTypeName)) {
                return true;
            }
        }
        return false;
    }

}
