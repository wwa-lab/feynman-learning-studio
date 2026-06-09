# Documentation

这个目录用来把项目知识放在代码旁边。
对这个学习型项目来说，docs 不是附属品，而是 Harness Loop 的一部分。

## Structure

- `00-project-rules/`：项目规则、开发标准、workflow、Harness Loop 和 gates。
- `01-specs/`：产品和 feature specs。新 spec 从 `01-specs/template.md` 开始。
- `02-design/`：architecture、module boundary、data model、API design 和 draw.io 图。
- `03-tasks/`：task list、execution manifest、implementation handoff。新任务从 `03-tasks/template.md` 开始。
- `04-runbooks/`：local test、local run、manual smoke、troubleshooting 和学习问题记录。
- `05-decisions/`：ADR 和长期 project decisions。
- `06-articles/`：article drafts、公开学习笔记、Feynman reflection 材料。
- `07-prompts/`：可复用 prompts，用于外部工具或 agent handoff。

阅读建议：

```text
00-project-rules
  -> 01-specs
  -> 02-design
  -> 03-tasks
  -> 04-runbooks
```
