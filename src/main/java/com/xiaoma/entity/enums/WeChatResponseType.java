package com.xiaoma.entity.enums;

public enum WeChatResponseType {

    TEXT("text"), IMAGE("image"), VOICE("voice"), VIDEO("video"), MUSIC("music"), ARTICLE("article");

    private String type;

    WeChatResponseType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
