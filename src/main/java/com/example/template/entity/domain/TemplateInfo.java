package com.example.template.entity.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

public class TemplateInfo implements Serializable {

    private Long id;
    @NotBlank(message = "产品编号不能为空")
    private String templateId;
    @NotBlank(message = "所属系列不能为空")
    private String templateSeries;
    @NotBlank(message = "规格不能为空")
    private String templateNorm;
    @Min(value = 1, message = "每套片数最小为1")
    private int piecesPerSuit;
    @NotBlank(message = "类型不能为空")
    private String templateType;
    @NotBlank(message = "签板人不能为空")
    private String templateSignaturer;
    private Date templateSignatureTime;
    @NotBlank(message = "色号不能为空")
    private String templateColor;
    @Past
    private Date templateCtime;

    public String getTemplateNorm() {
        return templateNorm;
    }

    public void setTemplateNorm(String templateNorm) {
        this.templateNorm = templateNorm;
    }

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

    public String getTemplateSeries() {
        return templateSeries;
    }

    public void setTemplateSeries(String templateSeries) {
        this.templateSeries = templateSeries;
    }

    public int getPiecesPerSuit() {
        return piecesPerSuit;
    }

    public void setPiecesPerSuit(int piecesPerSuit) {
        this.piecesPerSuit = piecesPerSuit;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getTemplateSignaturer() {
        return templateSignaturer;
    }

    public void setTemplateSignaturer(String templateSignaturer) {
        this.templateSignaturer = templateSignaturer;
    }

    public Date getTemplateSignatureTime() {
        return templateSignatureTime;
    }

    public void setTemplateSignatureTime(Date templateSignatureTime) {
        this.templateSignatureTime = templateSignatureTime;
    }

    public String getTemplateColor() {
        return templateColor;
    }

    public void setTemplateColor(String templateColor) {
        this.templateColor = templateColor;
    }

    public Date getTemplateCtime() {
        return templateCtime;
    }

    public void setTemplateCtime(Date templateCtime) {
        this.templateCtime = templateCtime;
    }

    @Override
    public String toString() {
        return "TemplateInfo{" +
                "id=" + id +
                ", templateId='" + templateId + '\'' +
                ", templateSeries='" + templateSeries + '\'' +
                ", templateNorm='" + templateNorm + '\'' +
                ", piecesPerSuit=" + piecesPerSuit +
                ", templateType='" + templateType + '\'' +
                ", templateSignaturer='" + templateSignaturer + '\'' +
                ", templateSignatureTime=" + templateSignatureTime +
                ", templateColor='" + templateColor + '\'' +
                ", templateCtime=" + templateCtime +
                '}';
    }
}
