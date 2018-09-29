package com.example.peterknut.maintainservice;

public class Document {
    //
    private Integer documentId;
    //资料类型
    private Long doctypeId;
    //资料类型名
    private String doctypeName;
    //标题
    private String title;
    //内容
    private String content;

    public Document() {
    }

    public String getDoctypeName() {
        return doctypeName;
    }

    public void setDoctypeName(String doctypeName) {
        this.doctypeName = doctypeName;
    }

    public Document(Integer documentId, Long doctypeId, String doctypeName, String title, String content) {

        this.documentId = documentId;
        this.doctypeId = doctypeId;
        this.doctypeName = doctypeName;
        this.title = title;
        this.content = content;
    }

    /**
     * 设置：
     */
    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }
    /**
     * 获取：
     */
    public Integer getDocumentId() {
        return documentId;
    }
    /**
     * 设置：资料类型
     */
    public void setDoctypeId(Long doctypeId) {
        this.doctypeId = doctypeId;
    }
    /**
     * 获取：资料类型
     */
    public Long getDoctypeId() {
        return doctypeId;
    }
    /**
     * 设置：标题
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * 获取：标题
     */
    public String getTitle() {
        return title;
    }
    /**
     * 设置：内容
     */
    public void setContent(String content) {
        this.content = content;
    }
    /**
     * 获取：内容
     */
    public String getContent() {
        return content;
    }
}
