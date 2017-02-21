package com.xiaoma.entity.response;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.xiaoma.entity.enums.WeChatResponseType;

public class WeChatResponse {

    private String toUserName;
    private String fromUserName;
    private WeChatResponseType msgType;
    private String content;
    private String mediaId;
    private String title;
    private String description;
    private String musicUrl;
    private String hQMusicUrl;
    private String thumbMediaId;
    private Integer articleCount;
    private List<Item> items = new ArrayList<Item>();

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public WeChatResponseType getMsgType() {
        return msgType;
    }

    public void setMsgType(WeChatResponseType msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String gethQMusicUrl() {
        return hQMusicUrl;
    }

    public void sethQMusicUrl(String hQMusicUrl) {
        this.hQMusicUrl = hQMusicUrl;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");

        if (StringUtils.isNotBlank(toUserName)) {
            sb.append("<ToUserName><![CDATA[" + toUserName + "]]></ToUserName>");
        }

        if (StringUtils.isNotBlank(fromUserName)) {
            sb.append("<FromUserName><![CDATA[" + fromUserName + "]]></FromUserName>");
        }

        sb.append("<CreateTime>" + System.currentTimeMillis() + "</CreateTime>");

        if (null != msgType) {
            sb.append("<MsgType><![CDATA[" + msgType.getType() + "]]></MsgType>");
        }

        switch (msgType) {
        case TEXT:
            if (StringUtils.isNotBlank(content)) {
                sb.append("<Content><![CDATA[" + content + "]]></Content>");
            }
            break;
        case IMAGE:
            if (StringUtils.isNotBlank(mediaId)) {
                sb.append("<Image><MediaId><![CDATA[" + mediaId + "]]></MediaId></Image>");
            }
            break;
        case VOICE:
            if (StringUtils.isNotBlank(mediaId)) {
                sb.append("<Voice><MediaId><![CDATA[" + mediaId + "]]></MediaId></Voice>");
            }
            break;
        case VIDEO:
            sb.append("<Video>");
            if (StringUtils.isNotBlank(mediaId)) {
                sb.append("<MediaId><![CDATA[" + mediaId + "]]></MediaId>");
            }
            if (StringUtils.isNotBlank(title)) {
                sb.append("<Title><![CDATA[" + title + "]]></Title>");
            }
            if (StringUtils.isNotBlank(description)) {
                sb.append("<Description><![CDATA[" + description + "]]></Description>");
            }
            sb.append("</Video>");
            break;
        case MUSIC:
            sb.append("<Music>");
            if (StringUtils.isNotBlank(title)) {
                sb.append("<Title><![CDATA[" + title + "]]></Title>");
            }
            if (StringUtils.isNotBlank(description)) {
                sb.append("<Description><![CDATA[" + description + "]]></Description>");
            }
            if (StringUtils.isNotBlank(musicUrl)) {
                sb.append("<MusicUrl><![CDATA[" + musicUrl + "]]></MusicUrl>");
            }
            if (StringUtils.isNotBlank(hQMusicUrl)) {
                sb.append("<HQMusicUrl><![CDATA[" + hQMusicUrl + "]]></HQMusicUrl>");
            }
            if (StringUtils.isNotBlank(thumbMediaId)) {
                sb.append("<ThumbMediaId><![CDATA[" + thumbMediaId + "]]></ThumbMediaId>");
            }
            sb.append("</Music>");
            break;
        case ARTICLE:
            if (null != articleCount) {
                sb.append("<ArticleCount>" + articleCount + "</ArticleCount>");
            }
            sb.append("<Articles>");
            for (Item item : items) {
                sb.append("<item>" + item + "</item>");
            }
            sb.append("</Articles>");
            break;
        default:
            break;
        }

        sb.append("</xml>");
        return sb.toString();
    }

    public class Item {

        private String title;
        private String description;
        private String picUrl;
        private String url;

        private Item() {
            super();
        }

        private Item(String title, String description, String picUrl, String url) {
            super();
            this.title = title;
            this.description = description;
            this.picUrl = picUrl;
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            StringBuffer sb = new StringBuffer();

            if (StringUtils.isNotBlank(title)) {
                sb.append("<Title><![CDATA[" + title + "]]></Title>");
            }

            if (StringUtils.isNotBlank(description)) {
                sb.append("<Description><![CDATA[" + description + "]]></Description>");
            }

            if (StringUtils.isNotBlank(picUrl)) {
                sb.append("<PicUrl><![CDATA[" + picUrl + "]]></PicUrl>");
            }

            if (StringUtils.isNotBlank(url)) {
                sb.append("<Url><![CDATA[" + url + "]]></Url>");
            }

            return sb.toString();
        }

    }

    public void addItem(String title, String description, String picUrl, String url) {
        items.add(new Item(title, description, picUrl, url));
    }

}
