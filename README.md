# Enterprise AI Agent Platform

Enterprise AI Agent Platform is a backend management system for building and managing AI Agent records in an enterprise-style admin console.

The project is built on top of the RuoYi-Vue framework, with custom AI Agent CRUD functionality added across the backend service layer, database mapper layer, API layer, and frontend management page.

## Features

- AI Agent list, query, create, update, delete, and export management.
- Spring Boot backend with layered controller, service, mapper, and domain structure.
- Vue admin page for AI Agent operations.
- MySQL persistence through MyBatis mapper XML.
- JWT-based authentication and permission integration inherited from the base admin framework.
- Local credential handling through environment variables instead of committed plaintext passwords.

## Tech Stack

- Backend: Java 17, Spring Boot 4, Spring Security, MyBatis, Druid, Redis
- Frontend: Vue, Element UI
- Database: MySQL
- Build: Maven

## Project Structure

```text
ruoyi-admin/      Application entry and web controllers
ruoyi-system/     Business domain, service, mapper, and XML mappings
ruoyi-framework/  Security, datasource, config, and framework support
ruoyi-common/     Shared utilities, annotations, and common models
ruoyi-ui/         Frontend application
doc/              Notes and learning records
```

## AI Agent Module

Key files:

```text
ruoyi-admin/src/main/java/com/ruoyi/ai/controller/AiAgentController.java
ruoyi-system/src/main/java/com/ruoyi/ai/domain/AiAgent.java
ruoyi-system/src/main/java/com/ruoyi/ai/mapper/AiAgentMapper.java
ruoyi-system/src/main/java/com/ruoyi/ai/service/IAiAgentService.java
ruoyi-system/src/main/java/com/ruoyi/ai/service/impl/AiAgentServiceImpl.java
ruoyi-system/src/main/resources/mapper/ai/AiAgentMapper.xml
ruoyi-ui/src/api/ai/agent.js
ruoyi-ui/src/views/ai/agent/index.vue
```

## Local Configuration

Database and Druid console credentials are configured through environment variables. Do not commit real passwords.

Required variables:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
DRUID_LOGIN_USERNAME
DRUID_LOGIN_PASSWORD
```

For VS Code, copy:

```text
.vscode/launch.example.json
```

to:

```text
.vscode/launch.json
```

Then replace the `change-me` values with your local credentials. The real `launch.json` file is ignored by git.

## Backend Startup

Build the backend:

```bash
mvn -pl ruoyi-admin -am package -DskipTests
```

Run the packaged application:

```bash
java -jar ruoyi-admin/target/ruoyi-admin.jar
```

Or start `com.ruoyi.RuoYiApplication` directly from VS Code with the local launch configuration.

## Frontend Startup

```bash
cd ruoyi-ui
npm install
npm run dev
```

## Update Log

Development updates are recorded in [UPDATE_README.md](UPDATE_README.md).

## Credits

This project uses RuoYi-Vue as the base framework and extends it with custom enterprise AI Agent management functionality.
