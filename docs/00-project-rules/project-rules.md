# Project Rules

## 产品定位

Feynman Learning Studio 是一个帮助工程师成为 Agent Engineer 的 Spec Driven AI Development 学习实践平台。

它不是 generic blog，不是 generic task tracker，也不是普通 CRUD demo。这个项目的中心是可运行、可验证、可解释的 learning experiment。

项目宪法是最高层规则文档：

```text
docs/00-project-rules/constitution.md
```

## 核心循环

每个有意义的产品能力，都应该强化这个学习循环：

```text
existing engineering experience
  -> transformation topic
  -> learning experiment
  -> spec
  -> AI/Agent-assisted implementation
  -> local test
  -> local run evidence
  -> AI explanation
  -> Feynman reflection
  -> reusable knowledge or article draft
```

对 IBM iSeries 背景的学习者来说，这个循环的重点是：把已有工程经验翻译成 AI 时代可交付、可验证、可解释的工作方式。

## 产品原则

- Evidence before explanation：先跑代码，再让 AI 解释结果。
- Human understanding before completion：AI summary 不能代替人的 Feynman reflection。
- Specs before implementation：spec 定义 scope、constraints 和 acceptance。
- Small runnable slices：每个版本都必须能独立测试、独立运行、独立讲清楚。
- Domain experience matters：旧系统、测试、运维、数据、业务经验是资产，不是包袱。

## Scope Discipline

v0.1 故意保持很小：

- Spring Boot 3.x backend。
- Learning Topic CRUD。
- Learning Experiment CRUD。
- Unified API response。
- Unified exception handling。
- Flyway database migration。
- H2 local tests。
- OpenAPI / Swagger。
- Local runbook。

除非用户明确改变 scope，否则 v0.1 不引入：

- Member、Team、TransformationPath、AgentWorkflow、RunEvidence。
- Redis、MQ、Nacos、Gateway。
- Frontend code。
- Authentication / authorization。
- Production deployment。

## 命名规则

代码里使用稳定的工程命名：

- `LearningTopic`
- `LearningExperiment`
- `LocalRun`
- `LocalTest`
- `Reflection`

文档和文章里可以使用更强的产品语言：

- transformation topic
- transformation experiment
- run evidence
- Feynman reflection
- Agent Engineer path

## 文档结构

项目知识必须放在 repo 里，而不是只留在聊天记录里。

- project rules：`docs/00-project-rules/`
- specs：`docs/01-specs/`
- architecture / design：`docs/02-design/`
- tasks / manifests：`docs/03-tasks/`
- runbooks：`docs/04-runbooks/`
- decisions：`docs/05-decisions/`
- articles / notes：`docs/06-articles/`
- prompts：`docs/07-prompts/`

不要无理由创建新的 top-level documentation folders。

## 标准模板

新增文档优先从现有 template 开始：

- spec template：`docs/01-specs/template.md`
- task template：`docs/03-tasks/template.md`
- execution manifest template：`docs/03-tasks/execution-manifest-template.yaml`

## Workflow 入口

SDD、Harness 和 skill 路由的主要入口：

- SDD pipeline：`docs/00-project-rules/sdd-pipeline.md`
- skill harness routing：`docs/00-project-rules/agent-skill-routing.md`
- harness loop：`docs/00-project-rules/harness-loop.md`
- harness gates：`docs/00-project-rules/harness-gates.md`

详细标准：

- design standards：`docs/00-project-rules/design-standards.md`
- backend standards：`docs/00-project-rules/backend-standards.md`
- frontend standards：`docs/00-project-rules/frontend-standards.md`

## Branch Strategy

分支策略定义在：

```text
docs/00-project-rules/workflow.md
```

核心规则：

- `main` 是唯一长期稳定分支。
- 新工作使用短生命周期 `codex/<slice-id>` branch。
- slice branch merge 到 `main` 后，确认 commit 已进入 `main`，再删除本地和远端 branch。
- 不默认使用长期 `develop` 分支。

## Skill Output Policy

Skills 提供 workflow scaffolding，不是最终 definition of done。

默认规则：

- 先保留 skill-native output。
- 不要把 project-specific additions 混进 skill 原始输出里。
- 如果 skill 输出不满足本 repo 的 SDD / Harness gates，就添加 companion supplement。

常见 supplement 形式：

- `{artifact}-supplement.md`
- 文档内明确标题：`Project Supplement`

不要因为 skill 产出了名义上的 output 就停止。只有当当前阶段的 source of truth、scope、acceptance criteria、implementation handoff 和 verification evidence 足够时，才算完成。

## Documentation Language

这个 repo 是个人学习和实践用的。项目文档默认使用中文。

保留清晰的技术名词、产品名、框架名、API 名、类名、文件路径、命令和代码标识符原文。

例子：

- Spring Boot
- MyBatis
- Flyway
- H2
- OpenAPI / Swagger
- LearningTopic / LearningExperiment
- CRUD / API / DTO
- `mvn test`

面向学习者的文档要尽量中文友好。特别是面向 IBM iSeries 背景的学习者时，需要补上下文、类比、预期输出和验证方式。

## External Actions

Networked tools 默认 read-only。

允许：

- inspect GitHub
- inspect docs
- inspect package registries

需要用户明确批准：

- push
- publish
- 修改 repository settings
- 创建 paid jobs
- posting content
- 修改 credentials
