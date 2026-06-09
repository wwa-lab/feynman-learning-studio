# Harness Gates

## 目的

Harness Gates 用来证明 AI 生成的文档、代码和学习材料符合本项目想要的风格。

它不是重流程，而是一个验收 checklist：

```text
代码能跑
  + 符合 spec
  + 符合 design
  + 有测试
  + 有本地 smoke
  + 有学习解释
  + 有问题记录
```

## Gate 总览

| Gate | 核心问题 | Evidence |
| --- | --- | --- |
| Spec Gate | 有没有超出 scope？ | active spec、non-goals、acceptance criteria |
| Skill Gate | 有没有保留 skill 原始输出？ | skill-native artifact、supplement |
| Design Gate | 结构是否符合设计？ | design、diagram、package/API/data model 对齐 |
| Test Gate | 自动化行为是否通过？ | test command output |
| Run Gate | 人能不能本地手动验证？ | Swagger、curl、local smoke evidence |
| Style Gate | 是否符合学习项目风格？ | 中文文档、IBM iSeries 友好解释、代码不过度抽象 |
| Learning Gate | 是否沉淀学习？ | runbook、踩坑记录、understand-anything code map、Feynman reflection prompts |
| Security Gate | 是否有明显安全问题？ | validation、SQL safety、secret handling、error handling |
| Migration Gate | 数据库变更是否可追踪？ | Flyway migration、schema history、runbook |

## Spec Gate

检查：

- 当前实现是否只做 active spec 允许的内容。
- 是否尊重 non-goals。
- 新增行为是否有 acceptance criteria。
- Open questions 是否已回答或明确暂缓。

不通过例子：

- v0.1 只要求 backend，却加入 frontend。
- spec 未定义 auth，却提前加入复杂权限模型。
- AI 因为“最佳实践”加入 Redis、MQ、Gateway。

## Skill Gate

检查：

- 是否使用了当前阶段对应 skill。
- skill 原始输出是否独立保存。
- 项目补充是否清楚标明。
- 补充是否是为了满足本 repo gate，而不是重写 skill 输出。

Evidence：

- `docs/02-design/*-architecture.md`
- `docs/02-design/*-architecture-supplement.md`
- `docs/03-tasks/*-tasks.md`
- `docs/03-tasks/*-tasks-supplement.md`

## Design Gate

检查：

- package 是否按 domain/feature 组织。
- controller / service / mapper / domain 边界是否清楚。
- data model 是否和 design / schema 对齐。
- API response envelope 是否统一。
- 错误码是否和 spec/design 对齐。

v0.1 backend 示例：

- `topic` 和 `experiment` 分包。
- controller 处理 HTTP boundary。
- service 处理业务规则。
- mapper 处理 SQL。
- 删除被引用 Topic 返回 `TOPIC_IN_USE`。

## Test Gate

检查：

- 新行为是否有对应测试。
- 测试是否覆盖 happy path、validation、not found、conflict、status transition。
- 测试命令是否实际运行。

Backend 默认命令：

```bash
cd backend && mvn test -Dspring.profiles.active=test
```

Evidence 格式：

```text
command:
result:
tests:
warnings:
```

## Run Gate

检查：

- 本地服务是否能启动。
- Swagger UI 是否能打开。
- OpenAPI JSON 是否可访问。
- 核心 API 是否能手动跑通。
- 关键错误路径是否可复现。

Backend 默认命令：

```bash
cd backend && mvn spring-boot:run -Dspring-boot.run.profiles=local
```

默认 URLs：

```text
http://localhost:8080/swagger-ui/index.html
http://localhost:8080/v3/api-docs
```

## Style Gate

检查：

- 项目文档是否默认中文。
- 技术名词是否保留英文而不是硬翻译。
- 面向学习者的文档是否假设读者没有 Java 经验。
- 对 IBM iSeries 背景的人是否有类比、上下文和可见结果。
- 代码是否简单、清楚、学习友好。
- 是否避免了当前 slice 不需要的企业级 abstraction。

用户反馈格式：

```text
Keep:
Change:
Avoid:
Rule:
```

## Learning Gate

检查：

- runbook 是否说明如何设置环境、运行测试、启动服务、手动验证。
- 学习过程中的问题是否有记录入口。
- non-trivial slice 是否运行或刷新了 `understand-anything` code map。
- 是否把 dashboard / knowledge graph 中的关键阅读路径转成中文导读，而不是只留下机器生成的 JSON。
- 是否能用 AS/400 / IBM iSeries 背景能理解的方式解释 controller / service / mapper / SQL / test 的关系。
- Feynman reflection prompts 是否存在。
- 是否能回答“我学到了什么”和“如何讲给别人听”。

学习问题默认记录到当前 slice runbook。

当问题变成长期设计选择时，升级到 `docs/05-decisions/`。

`understand-anything` 使用建议：

```bash
/understand --language zh
```

默认不把 `.understand-anything/` 视为长期 source of truth。它更像可再生成的阅读索引。应该提交到 git 的，是从图谱中整理出来的中文 reading guide、runbook evidence、decision 或 article。

## Security Gate

检查：

- request body、query、path variable 是否验证。
- SQL 是否通过 MyBatis 参数化，不拼接用户输入。
- 是否没有 hardcoded secrets。
- error response 是否不泄露内部细节。
- 日志是否足够诊断但不暴露敏感数据。

如果涉及 auth、外部集成、凭证、生产配置，必须扩大 security review。

## Migration Gate

检查：

- schema 变更是否通过 Flyway migration 管理。
- migration 是否位于 `backend/src/main/resources/db/migration/`。
- migration 命名是否使用 Flyway versioned migration 格式，例如 `V1__create_learning_core_tables.sql`。
- 是否避免使用临时 `schema.sql` 管理 application schema。
- 测试是否能证明 migration 已执行，例如检查 `flyway_schema_history`。
- runbook 是否说明 schema 由 Flyway 管理。

## Gate 结果格式

每个 slice 的 manifest 或 runbook 应记录：

```yaml
harness_gates:
  spec:
    status: pass
    evidence: ""
  skill:
    status: pass
    evidence: ""
  design:
    status: pass
    evidence: ""
  test:
    status: pass
    evidence: ""
  run:
    status: pass
    evidence: ""
  style:
    status: pass
    evidence: ""
  learning:
    status: pass
    evidence: ""
  security:
    status: pass
    evidence: ""
  migration:
    status: pass
    evidence: ""
```

允许状态：

- `pass`
- `fail`
- `skipped`
- `deferred`

`skipped` 和 `deferred` 必须写原因。
