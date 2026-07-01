# RuoYi-Vue 项目架构学习笔记

## 一、项目概述

这是一个基于 **Spring Boot 4.0.6 + Java 17** 的企业级后端管理系统框架。

┌─────────────────────────────────────────────────────────────────────────┐
│                         项目模块架构                                    │
├─────────────────────────────────────────────────────────────────────────┤
│                                                                         │
│   ┌─────────────┐    ┌─────────────┐    ┌─────────────┐               │
│   │  ruoyi-admin │←──│ ruoyi-framework │←──│ ruoyi-system │           │
│   │  (启动模块)   │    │  (核心框架)    │    │  (业务模块)   │           │
│   └──────┬──────┘    └──────┬──────┘    └──────┬──────┘               │
│          │                  │                  │                        │
│          │                  ▼                  │                        │
│          │           ┌─────────────┐           │                        │
│          │           │  ruoyi-common│           │                        │
│          │           │  (通用工具)   │           │                        │
│          │           └─────────────┘           │                        │
│          │                                     │                        │
│          │                  ▲                  │                        │
│          └──────────────────┼──────────────────┘                        │
│                             │                                          │
│                   ┌─────────┴─────────┐                                │
│                   │   ruoyi-quartz    │                                │
│                   │   ruoyi-generator │                                │
│                   └───────────────────┘                                │
│                                                                         │
└─────────────────────────────────────────────────────────────────────────┘



### 技术栈

| 分类 | 技术 | 版本 |
|------|------|------|
| 语言 | Java | 17 |
| 框架 | Spring Boot | 4.0.6 |
| ORM | MyBatis | 4.0.1 |
| 数据库连接池 | Druid | 1.2.28 |
| 缓存 | Redis | - |
| 认证 | JWT | 0.9.1 |
| API文档 | SpringDoc OpenAPI | 3.0.3 |

---

## 二、模块架构

### 模块职责划分

| 模块 | 职责 | 核心内容 |
|------|------|----------|
| **ruoyi-admin** | 启动入口、控制器层 | Controller、配置文件、启动类 |
| **ruoyi-framework** | 核心框架支撑 | 安全认证、AOP切面、拦截器 |
| **ruoyi-system** | 业务逻辑层 | Service、Mapper、Domain实体 |
| **ruoyi-common** | 通用工具封装 | 工具类、常量、注解、异常 |
| **ruoyi-quartz** | 定时任务管理 | 任务调度 |
| **ruoyi-generator** | 代码生成器 | 模板引擎 |

### 模块依赖关系

ruoyi-admin (启动模块) ├── ruoyi-framework (核心框架) ├── ruoyi-system (业务模块) │ └── ruoyi-common (通用工具) ├── ruoyi-quartz (定时任务) └── ruoyi-generator (代码生成)
---

## 三、分层架构

### 1. Controller 层

ruoyi-admin/src/main/java/com/ruoyi/web/controller/
├── common/        # 通用接口（验证码、文件上传等）
├── monitor/       # 监控管理（日志、在线用户、缓存等）
├── system/        # 系统管理（用户、角色、菜单、部门等）
└── tool/          # 工具模块（测试接口等）


**位置**: `ruoyi-admin/src/main/java/com/ruoyi/web/controller/`

**子目录**:
- `common/` - 通用接口（验证码、文件上传等）
- `monitor/` - 监控管理（日志、在线用户、缓存）
- `system/` - 系统管理（用户、角色、菜单、部门）
- `tool/` - 工具模块

**设计特点**:
- 继承 `BaseController`，统一返回 `AjaxResult`
- 使用 `@Log` 注解自动记录操作日志

### 2. Service 层

ruoyi-system/src/main/java/com/ruoyi/system/service/
├── ISysUserService.java      # 接口定义
├── ISysRoleService.java
└── impl/
    └── SysUserServiceImpl.java  # 实现类


**位置**: `ruoyi-system/src/main/java/com/ruoyi/system/service/`

**设计模式**: 接口 + 实现类分离
- 接口：`ISysUserService.java`
- 实现：`impl/SysUserServiceImpl.java`

### 3. Mapper 层

ruoyi-system/src/main/java/com/ruoyi/system/mapper/
├── SysUserMapper.java
├── SysRoleMapper.java
└── ...


**位置**: `ruoyi-system/src/main/java/com/ruoyi/system/mapper/`

**特点**: 基于 MyBatis 接口式编程，SQL 在 XML 中定义

### 4. Domain 层

ruoyi-common/src/main/java/com/ruoyi/common/core/domain/
├── entity/           # 实体基类（SysUser、SysRole等）
├── model/            # 业务模型（LoginUser、LoginBody等）
├── AjaxResult.java   # 统一响应封装
├── BaseEntity.java   # 实体基类（含创建时间、更新时间等）
└── R.java            # 通用响应包装


**位置**: `ruoyi-common/src/main/java/com/ruoyi/common/core/domain/`

- `entity/` - 实体类（SysUser、SysRole等）
- `model/` - 业务模型（LoginUser、LoginBody等）
- `AjaxResult.java` - 统一响应封装

---

## 四、核心组件

### 1. 安全认证体系（JWT）

ruoyi-framework/src/main/java/com/ruoyi/framework/security/
├── filter/
│   └── JwtAuthenticationTokenFilter.java  # JWT Token 过滤器
├── service/
│   ├── TokenService.java                   # Token 管理服务
│   └── UserDetailsServiceImpl.java         # 用户详情服务
└── handle/
    ├── AuthenticationEntryPointImpl.java   # 未认证处理
    └── LogoutSuccessHandlerImpl.java       # 登出处理


**认证流程**:
1. 用户登录 → 生成 JWT Token
2. 请求携带 Token → JwtAuthenticationTokenFilter 验证
3. 解析 Token → 存入 SecurityContext
4. 权限校验 → 访问资源

**关键类**:
- `JwtAuthenticationTokenFilter.java` - Token 过滤器
- `TokenService.java` - Token 管理服务
- `UserDetailsServiceImpl.java` - 用户详情服务

### 2. AOP 切面处理

ruoyi-framework/src/main/java/com/ruoyi/framework/aspectj/
├── LogAspect.java           # 操作日志记录
├── DataScopeAspect.java     # 数据权限控制
├── DataSourceAspect.java    # 多数据源切换
└── RateLimiterAspect.java   # 限流处理


**位置**: `ruoyi-framework/src/main/java/com/ruoyi/framework/aspectj/`

| 切面类 | 功能 |
|--------|------|
| `LogAspect.java` | 操作日志记录 |
| `DataScopeAspect.java` | 数据权限控制 |
| `DataSourceAspect.java` | 多数据源切换 |
| `RateLimiterAspect.java` | 限流处理 |

### 3. 全局异常处理

ruoyi-framework/src/main/java/com/ruoyi/framework/web/exception/
└── GlobalExceptionHandler.java

**位置**: `ruoyi-framework/src/main/java/com/ruoyi/framework/web/exception/GlobalExceptionHandler.java`

统一处理各类异常，返回标准化错误响应。

---
## 目录结构总揽
RuoYi-Vue/
├── ruoyi-admin/              # 启动模块（打包部署）
│   ├── src/main/java/com/ruoyi/
│   │   ├── RuoYiApplication.java    # Spring Boot 启动类
│   │   └── web/controller/          # REST API 控制器
│   └── src/main/resources/
│       ├── application.yml          # 应用配置
│       └── application-druid.yml    # 数据源配置
├── ruoyi-framework/          # 核心框架
│   ├── aspectj/              # AOP 切面
│   ├── config/               # 配置类
│   ├── security/             # 安全认证
│   ├── interceptor/          # 拦截器
│   └── web/service/          # 框架级服务
├── ruoyi-system/             # 业务模块
│   ├── domain/               # 实体类
│   ├── mapper/               # 数据访问层
│   └── service/              # 业务逻辑层
├── ruoyi-common/             # 通用工具
│   ├── annotation/           # 自定义注解
│   ├── constant/             # 常量定义
│   ├── core/                 # 核心工具类
│   └── enums/                # 枚举类
├── ruoyi-quartz/             # 定时任务
└── ruoyi-generator/          # 代码生成器


## 五、设计亮点

1. **模块化拆分**：职责清晰，易于扩展
2. **安全体系**：JWT + Spring Security，无状态认证
3. **权限控制**：支持按钮级权限、数据范围权限
4. **切面增强**：日志、限流等通过 AOP 实现
5. **统一响应**：`AjaxResult` 封装，前端处理统一
6. **代码生成**：支持 CRUD 代码自动生成

---

## 六、关键文件路径速查

| 文件 | 路径 |
|------|------|
| 启动类 | `ruoyi-admin/src/main/java/com/ruoyi/RuoYiApplication.java` |
| 应用配置 | `ruoyi-admin/src/main/resources/application.yml` |
| 数据源配置 | `ruoyi-admin/src/main/resources/application-druid.yml` |
| 安全配置 | `ruoyi-framework/src/main/java/com/ruoyi/framework/config/SecurityConfig.java` |
| JWT过滤器 | `ruoyi-framework/src/main/java/com/ruoyi/framework/security/filter/JwtAuthenticationTokenFilter.java` |
| 全局异常处理 | `ruoyi-framework/src/main/java/com/ruoyi/framework/web/exception/GlobalExceptionHandler.java` |
| 用户实体 | `ruoyi-common/src/main/java/com/ruoyi/common/core/domain/entity/SysUser.java` |

---

## 学习进度

- [x] 了解项目整体架构
- [x] 理解模块划分及职责
- [x] 掌握分层架构设计
- [ ] 深入学习安全认证机制
- [ ] 学习AOP切面实现
- [ ] 理解数据权限控制
