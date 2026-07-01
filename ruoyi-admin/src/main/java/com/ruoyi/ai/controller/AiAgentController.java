package com.ruoyi.ai.controller;

import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.ai.domain.AiAgent;
import com.ruoyi.ai.service.IAiAgentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * AI AgentController
 * 
 * @author ruoyi
 * @date 2026-07-01
 */
@RestController
@RequestMapping("/ai/agent")
public class AiAgentController extends BaseController
{
    @Autowired
    private IAiAgentService aiAgentService;

    /**
     * 查询AI Agent列表
     */
    @PreAuthorize("@ss.hasPermi('ai:agent:list')")
    @GetMapping("/list")
    public TableDataInfo list(AiAgent aiAgent)
    {
        startPage();
        List<AiAgent> list = aiAgentService.selectAiAgentList(aiAgent);
        return getDataTable(list);
    }

    /**
     * 导出AI Agent列表
     */
    @PreAuthorize("@ss.hasPermi('ai:agent:export')")
    @Log(title = "AI Agent", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AiAgent aiAgent)
    {
        List<AiAgent> list = aiAgentService.selectAiAgentList(aiAgent);
        ExcelUtil<AiAgent> util = new ExcelUtil<AiAgent>(AiAgent.class);
        util.exportExcel(response, list, "AI Agent数据");
    }

    /**
     * 获取AI Agent详细信息
     */
    @PreAuthorize("@ss.hasPermi('ai:agent:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(aiAgentService.selectAiAgentById(id));
    }

    /**
     * 新增AI Agent
     */
    @PreAuthorize("@ss.hasPermi('ai:agent:add')")
    @Log(title = "AI Agent", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AiAgent aiAgent)
    {
        return toAjax(aiAgentService.insertAiAgent(aiAgent));
    }

    /**
     * 修改AI Agent
     */
    @PreAuthorize("@ss.hasPermi('ai:agent:edit')")
    @Log(title = "AI Agent", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AiAgent aiAgent)
    {
        return toAjax(aiAgentService.updateAiAgent(aiAgent));
    }

    /**
     * 删除AI Agent
     */
    @PreAuthorize("@ss.hasPermi('ai:agent:remove')")
    @Log(title = "AI Agent", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(aiAgentService.deleteAiAgentByIds(ids));
    }
}
