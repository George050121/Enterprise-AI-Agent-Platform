package com.ruoyi.ai.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * AI Agent对象 ai_agent
 * 
 * @author ruoyi
 * @date 2026-07-01
 */
public class AiAgent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** Agent ID */
    private Long id;

    /** Agent名称 */
    @Excel(name = "Agent名称")
    private String name;

    /** Agent描述 */
    @Excel(name = "Agent描述")
    private String description;

    /** 模型供应商 */
    @Excel(name = "模型供应商")
    private String modelProvider;

    /** 模型名称 */
    @Excel(name = "模型名称")
    private String modelName;

    /** 系统提示词 */
    @Excel(name = "系统提示词")
    private String systemPrompt;

    /** 温度参数 */
    @Excel(name = "温度参数")
    private BigDecimal temperature;

    /** 状态：0正常 1停用 */
    @Excel(name = "状态：0正常 1停用")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setModelProvider(String modelProvider) 
    {
        this.modelProvider = modelProvider;
    }

    public String getModelProvider() 
    {
        return modelProvider;
    }

    public void setModelName(String modelName) 
    {
        this.modelName = modelName;
    }

    public String getModelName() 
    {
        return modelName;
    }

    public void setSystemPrompt(String systemPrompt) 
    {
        this.systemPrompt = systemPrompt;
    }

    public String getSystemPrompt() 
    {
        return systemPrompt;
    }

    public void setTemperature(BigDecimal temperature) 
    {
        this.temperature = temperature;
    }

    public BigDecimal getTemperature() 
    {
        return temperature;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("description", getDescription())
            .append("modelProvider", getModelProvider())
            .append("modelName", getModelName())
            .append("systemPrompt", getSystemPrompt())
            .append("temperature", getTemperature())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
