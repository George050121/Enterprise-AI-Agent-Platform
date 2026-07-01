package com.ruoyi.ai.service;

import java.util.List;
import com.ruoyi.ai.domain.AiAgent;

/**
 * AI AgentService接口
 * 
 * @author ruoyi
 * @date 2026-07-01
 */
public interface IAiAgentService 
{
    /**
     * 查询AI Agent
     * 
     * @param id AI Agent主键
     * @return AI Agent
     */
    public AiAgent selectAiAgentById(Long id);

    /**
     * 查询AI Agent列表
     * 
     * @param aiAgent AI Agent
     * @return AI Agent集合
     */
    public List<AiAgent> selectAiAgentList(AiAgent aiAgent);

    /**
     * 新增AI Agent
     * 
     * @param aiAgent AI Agent
     * @return 结果
     */
    public int insertAiAgent(AiAgent aiAgent);

    /**
     * 修改AI Agent
     * 
     * @param aiAgent AI Agent
     * @return 结果
     */
    public int updateAiAgent(AiAgent aiAgent);

    /**
     * 批量删除AI Agent
     * 
     * @param ids 需要删除的AI Agent主键集合
     * @return 结果
     */
    public int deleteAiAgentByIds(Long[] ids);

    /**
     * 删除AI Agent信息
     * 
     * @param id AI Agent主键
     * @return 结果
     */
    public int deleteAiAgentById(Long id);
}
