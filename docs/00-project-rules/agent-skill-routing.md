# Skill Harness Routing

这个文档把 Control Tower 的 skill-routing 思路，改造成适合本项目的 Codex-centered Harness + loop + skill workflow。

## 规则

当一个任务明显属于某个 SDD stage 时，优先使用对应 skill，不要临场发明新流程。

本项目默认不使用 multi-agent execution。
这里的核心单位是 skill，验收方式是 harness gates。

## Stage Routing

| Stage | Primary skill | Harness gate | Output |
| --- | --- | --- | --- |
| 原始想法或笔记 -> stories | `req-to-user-story` | clarity gate | user stories 或 engineer stories |
| Stories -> spec | `user-story-to-spec` | spec gate | 面向 implementation 的 spec |
| Spec -> architecture | `spec-to-architecture` | design gate | architecture notes |
| Architecture/spec -> diagram | `drawio-skill` | explanation gate | 可编辑 `.drawio` diagram |
| Architecture/spec -> design | `architecture-to-design` | design gate | design、data model、API guide |
| Design -> tasks | `design-to-tasks` | execution gate | phased task list |
| Tasks -> code | `tasks-to-implementation`, `tdd-workflow` | test gate | code、tests、docs |
| Doc quality review | `review-doc-quality` | doc gate | doc readiness report |
| Code vs design review | `review-code-against-design` | alignment gate | implementation alignment findings |
| Security review | `security-review` | security gate | security findings |
| Verification | `verification-loop` | evidence gate | verification evidence |

## Project Loop Responsibilities

下面这些是一个 loop 里的职责，不是要启动多个 agent：

- Scope owner：保证当前 slice 不超出 spec 和 non-goals。
- Evidence gatherer：需要时检查 repo 文件、命令输出、日志和 framework docs。
- Skill runner：应用选定 skill，并保留 skill-native output。
- Implementer：做最小有用代码或文档变更。
- Harness checker：运行 tests、smoke、review diff，并检查 style。
- Learning recorder：更新 runbooks、踩坑记录和 Feynman reflection prompts。

## Diagram Rule

当设计有 3 个以上组件、状态流转、数据关系或 handoff 时，主动使用 `drawio-skill`。

Diagram 不是装饰，它应该帮助学习者解释系统。

## Backend Learning Slice 默认流程

Backend learning slice 默认走：

```text
spec skill
  -> architecture/design skill
  -> draw.io design diagram
  -> task skill
  -> TDD implementation loop
  -> verification-loop
  -> runbook and learning notes
```

实现仍然保持小：

- Spring Boot 3.x
- MyBatis
- Flyway
- H2 local tests
- Topic / Experiment CRUD
- unified response
- unified exception handling

## Handoff Notes

每个 loop 都要留下 evidence，而不是只留下观点：

- 检查了哪些文件，以及发现了什么事实。
- scope、non-goals、acceptance criteria、open questions。
- boundary decisions 和 risks。
- 修改了哪些文件、增加了哪些 tests、运行了哪些 verification commands。
- harness gate results。
- 如涉及安全面，记录 security findings 和 residual risks。
- 当 framework 行为重要时，记录使用的 primary docs 或 repo files。

## Cross-Tool Compatibility

不能直接加载 Codex skills 的工具，可以把这个文件当作 routing map，然后手动打开相关 repo docs 和 skill files。

长期项目事实必须进入 repo。临时 working notes 可以留在当前 thread。

## Codex And Claude Code Split

Codex 默认是 producer：

- create or update specs
- create tasks and manifests
- implement code
- add tests
- run local verification
- update runbooks and docs

Claude Code 是可选 independent reviewer。只有在用户要求，或变更风险足够高需要独立 review gate 时使用。

Claude Code review 重点：

- docs phase quality and traceability
- code against specs, design, and tasks
- security-sensitive changes
- unclear harness evidence
- missing tests or behavioral regressions

请求 external review 时，提供：

- `AGENTS.md`
- active spec
- task list
- execution manifest
- relevant diff
- verification command output
- known risks or skipped checks
