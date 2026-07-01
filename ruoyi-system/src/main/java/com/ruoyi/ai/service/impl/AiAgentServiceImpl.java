package com.ruoyi.ai.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.ai.mapper.AiAgentMapper;
import com.ruoyi.ai.domain.AiAgent;
import com.ruoyi.ai.service.IAiAgentService;

/**
 * AI AgentService业务层处理
 * 
 * @author ruoyi
 * @date 2026-07-01
 */
@Service
public class AiAgentServiceImpl implements IAiAgentService 
{
    @Autowired
    private AiAgentMapper aiAgentMapper;

    /**
     * 查询AI Agent
     * 
     * @param id AI Agent主键
     * @return AI Agent
     */
    @Override
    public AiAgent selectAiAgentById(Long id)
    {
        return aiAgentMapper.selectAiAgentById(id);
    }

    /**
     * 查询AI Agent列表
     * 
     * @param aiAgent AI Agent
     * @return AI Agent
     */
    @Override
    public List<AiAgent> selectAiAgentList(AiAgent aiAgent)
    {
        return aiAgentMapper.selectAiAgentList(aiAgent);
    }

    /**
     * 新增AI Agent
     * 
     * @param aiAgent AI Agent
     * @return 结果
     */
    @Override
    public int insertAiAgent(AiAgent aiAgent)
    {
        aiAgent.setCreateTime(DateUtils.getNowDate());
        return aiAgentMapper.insertAiAgent(aiAgent);
    }

    /**
     * 修改AI Agent
     * 
     * @param aiAgent AI Agent
     * @return 结果
     */
    @Override
    public int updateAiAgent(AiAgent aiAgent)
    {
        aiAgent.setUpdateTime(DateUtils.getNowDate());
        return aiAgentMapper.updateAiAgent(aiAgent);
    }

    /**
     * 批量删除AI Agent
     * 
     * @param ids 需要删除的AI Agent主键
     * @return 结果
     */
    @Override
    public int deleteAiAgentByIds(Long[] ids)
    {
        return aiAgentMapper.deleteAiAgentByIds(ids);
    }

    /**
     * 删除AI Agent信息
     * 
     * @param id AI Agent主键
     * @return 结果
     */
    @Override
    public int deleteAiAgentById(Long id)
    {
        return aiAgentMapper.deleteAiAgentById(id);
    }
}
