# Feynman Learning Studio

Feynman Learning Studio 是一个 Spec Driven AI Development 学习实践平台，目标是帮助普通工程师逐步成为 Agent Engineer。

它不是一个普通 CRUD demo，也不是单纯写文档的练习。项目的核心是：

```text
可运行的 learning experiment
  -> 本地 test/run evidence
  -> AI/Agent 协作
  -> Feynman reflection
  -> 可复用的工程知识
```

IBM iSeries modernization 是第一个真实转型路径，但不是平台边界。

## Project Setup

第一次阅读项目时，建议先从这些规则文档开始：

- [Agent rules](AGENTS.md)：AI / Codex 在这个 repo 里要遵守的最高优先级规则。
- [Constitution](docs/00-project-rules/constitution.md)：项目宪法，定义不可轻易改变的原则。
- [Project rules](docs/00-project-rules/project-rules.md)：产品定位、文档结构、scope discipline。
- [Development standards](docs/00-project-rules/development-standards.md)：开发标准总入口。
- [Design standards](docs/00-project-rules/design-standards.md)：设计和 diagram 规则。
- [Backend standards](docs/00-project-rules/backend-standards.md)：Spring Boot / MyBatis / Flyway 等 backend 规则。
- [Frontend standards](docs/00-project-rules/frontend-standards.md)：未来 Vue2 + ElementUI frontend 规则。
- [Workflow](docs/00-project-rules/workflow.md)：Harness Loop、branch strategy、verification gates。
- [SDD pipeline](docs/00-project-rules/sdd-pipeline.md)：spec -> design -> tasks -> implementation 的流程。
- [Skill harness routing](docs/00-project-rules/agent-skill-routing.md)：不同阶段该用哪些 skill。

## Current Stage

v0.1 是第一个 Spring Boot 3.x backend learning slice，已经包含：

- Learning Topic CRUD.
- Learning Experiment CRUD.
- Unified API response.
- Unified exception handling.
- Flyway database migration.
- H2 local tests.
- OpenAPI/Swagger.
- Local runbook.

手动验证入口：

- [v0.1 Backend Runbook](docs/04-runbooks/v0.1-backend-learning-core-runbook.md)
- [v0.1 VS Code 手动验证指南](docs/04-runbooks/v0.1-vscode-manual-verification-guide.md)
