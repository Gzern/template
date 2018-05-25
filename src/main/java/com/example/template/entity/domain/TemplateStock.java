package com.example.template.entity.domain;

import java.io.Serializable;
import java.util.Date;

public class TemplateStock implements Serializable {

    private Long id;
    private String templateId;
    private int templateAmount;
    private double templateSuits;
    private String templateAreas;
    private String templateFactory;
    private String templateStore;
    private String templateMemo;
    private Boolean templateBorrowed = false;
    private Date templateCtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public int getTemplateAmount() {
        return templateAmount;
    }

    public void setTemplateAmount(int templateAmount) {
        this.templateAmount = templateAmount;
    }

    public double getTemplateSuits() {
        return templateSuits;
    }

    public void setTemplateSuits(double templateSuits) {
        this.templateSuits = templateSuits;
    }

    public String getTemplateAreas() {
        return templateAreas;
    }

    public void setTemplateAreas(String templateAreas) {
        this.templateAreas = templateAreas;
    }

    public String getTemplateFactory() {
        return templateFactory;
    }

    public void setTemplateFactory(String templateFactory) {
        this.templateFactory = templateFactory;
    }

    public String getTemplateStore() {
        return templateStore;
    }

    public void setTemplateStore(String templateStore) {
        this.templateStore = templateStore;
    }

    public String getTemplateMemo() {
        return templateMemo;
    }

    public void setTemplateMemo(String templateMemo) {
        this.templateMemo = templateMemo;
    }

    public Boolean getTemplateBorrowed() {
        return templateBorrowed;
    }

    public void setTemplateBorrowed(Boolean templateBorrowed) {
        this.templateBorrowed = templateBorrowed;
    }

    public Date getTemplateCtime() {
        return templateCtime;
    }

    public void setTemplateCtime(Date templateCtime) {
        this.templateCtime = templateCtime;
    }

    @Override
    public String toString() {
        return "TemplateStock{" +
                "id=" + id +
                ", templateId='" + templateId + '\'' +
                ", templateAmount=" + templateAmount +
                ", templateSuits=" + templateSuits +
                ", templateAreas='" + templateAreas + '\'' +
                ", templateFactory='" + templateFactory + '\'' +
                ", templateStore='" + templateStore + '\'' +
                ", templateMemo='" + templateMemo + '\'' +
                ", templateBorrowed=" + templateBorrowed +
                ", templateCtime=" + templateCtime +
                '}';
    }
}
