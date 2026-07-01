import request from '@/utils/request'

// 查询AI Agent列表
export function listAgent(query) {
  return request({
    url: '/ai/agent/list',
    method: 'get',
    params: query
  })
}

// 查询AI Agent详细
export function getAgent(id) {
  return request({
    url: '/ai/agent/' + id,
    method: 'get'
  })
}

// 新增AI Agent
export function addAgent(data) {
  return request({
    url: '/ai/agent',
    method: 'post',
    data: data
  })
}

// 修改AI Agent
export function updateAgent(data) {
  return request({
    url: '/ai/agent',
    method: 'put',
    data: data
  })
}

// 删除AI Agent
export function delAgent(id) {
  return request({
    url: '/ai/agent/' + id,
    method: 'delete'
  })
}
