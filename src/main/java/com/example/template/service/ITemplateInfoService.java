package com.example.template.service;

import com.example.template.entity.domain.TemplateInfo;

import java.io.IOException;
import java.util.Map;

public interface ITemplateInfoService {
    public boolean addTemplateInfo(TemplateInfo templateInfo);

    public boolean deleteTemplateInfo(TemplateInfo templateInfo) throws IOException;

    public boolean updateTemplateInfo(TemplateInfo templateInfo);

    public Map<String, Object> listTemplateInfos(String templateId, int startPage, int pageSize);

    public TemplateInfo findTemplateInfoByTemplateId(String templateId);

    public boolean existed(TemplateInfo templateInfo);

}
