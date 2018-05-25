package com.example.template.mapper;

import com.example.template.entity.domain.TemplateInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ITemplateInfoMapper {

    public void addTemplate(TemplateInfo templateInfo);

    public void deleteTemplateByTemplateId(TemplateInfo templateInfo);

    public void updateTemplateByTemplateId(TemplateInfo templateInfo);

    public TemplateInfo findTemplateByTemplateInfoId(TemplateInfo templateInfo);

    public List<TemplateInfo> listAllTemplatesOrByTemplateId(@Param("templateId") String templateId);


}
