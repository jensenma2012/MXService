package com.xiaoma.entity.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;

@XmlRootElement(name = "xml")
public class WeChatMessage {

    private String toUserName;
    private String fromUserName;
    private Long createTime;
    private String msgType;
    private String event;
    private String content;
    private String picUrl;
    private String mediaId;
    private String thumbMediaId;
    private String format;
    private String recognition;
    private String title;
    private String description;
    private String url;
    private Double lcation_X;
    private Double lcation_Y;
    private Double scale;
    private String label;
    private Long msgId;

    @XmlElement(name = "ToUserName")
    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    @XmlElement(name = "FromUserName")
    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    @XmlElement(name = "CreateTime")
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @XmlElement(name = "MsgType")
    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    @XmlElement(name = "Event")
    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @XmlElement(name = "Content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @XmlElement(name = "PicUrl")
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @XmlElement(name = "MediaId")
    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    @XmlElement(name = "ThumbMediaId")
    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    @XmlElement(name = "Format")
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @XmlElement(name = "Recognition")
    public String getRecognition() {
        return recognition;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }

    @XmlElement(name = "Title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement(name = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement(name = "Url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlElement(name = "Lcation_X")
    public Double getLcation_X() {
        return lcation_X;
    }

    public void setLcation_X(Double lcation_X) {
        this.lcation_X = lcation_X;
    }

    @XmlElement(name = "Lcation_Y")
    public Double getLcation_Y() {
        return lcation_Y;
    }

    public void setLcation_Y(Double lcation_Y) {
        this.lcation_Y = lcation_Y;
    }

    @XmlElement(name = "Scale")
    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    @XmlElement(name = "Label")
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @XmlElement(name = "MsgId")
    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
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

        if (null != createTime) {
            sb.append("<CreateTime>" + createTime + "</CreateTime>");
        }

        if (StringUtils.isNotBlank(msgType)) {
            sb.append("<MsgType><![CDATA[" + msgType + "]]></MsgType>");
        }

        if (StringUtils.isNotBlank(event)) {
            sb.append("<Event><![CDATA[" + event + "]]></Event>");
        }

        if (StringUtils.isNotBlank(content)) {
            sb.append("<Content><![CDATA[" + content + "]]></Content>");
        }

        if (StringUtils.isNotBlank(picUrl)) {
            sb.append("<PicUrl><![CDATA[" + picUrl + "]]></PicUrl>");
        }

        if (StringUtils.isNotBlank(mediaId)) {
            sb.append("<MediaId><![CDATA[" + mediaId + "]]></MediaId>");
        }

        if (StringUtils.isNotBlank(thumbMediaId)) {
            sb.append("<ThumbMediaId><![CDATA[" + thumbMediaId + "]]></ThumbMediaId>");
        }

        if (StringUtils.isNotBlank(format)) {
            sb.append("<Format><![CDATA[" + format + "]]></Format>");
        }

        if (StringUtils.isNotBlank(recognition)) {
            sb.append("<Recognition><![CDATA[" + recognition + "]]></Recognition>");
        }

        if (StringUtils.isNotBlank(title)) {
            sb.append("<Title><![CDATA[" + title + "]]></Title>");
        }

        if (StringUtils.isNotBlank(description)) {
            sb.append("<Description><![CDATA[" + description + "]]></Description>");
        }

        if (StringUtils.isNotBlank(url)) {
            sb.append("<Url><![CDATA[" + url + "]]></Url>");
        }

        if (null != lcation_X) {
            sb.append("<Lcation_X>" + lcation_X + "</Lcation_X>");
        }

        if (null != lcation_Y) {
            sb.append("<Lcation_Y>" + lcation_Y + "</Lcation_Y>");
        }

        if (null != scale) {
            sb.append("<Scale>" + scale + "</Scale>");
        }

        if (StringUtils.isNotBlank(label)) {
            sb.append("<Label><![CDATA[" + label + "]]></Label>");
        }

        if (null != msgId) {
            sb.append("<MsgId>" + msgId + "</MsgId>");
        }

        sb.append("</xml>");
        return sb.toString();
    }

}
