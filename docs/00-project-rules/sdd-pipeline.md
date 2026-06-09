# SDD Pipeline

这个文档把 `wwa-lab/Agentic-SDLC-Control-Tower` 的经验，调整成适合 Feynman Learning Studio 的轻量 SDD pipeline。

Control Tower 是完整 enterprise SDD chain。
本项目默认使用更轻的 profile，因为当前目标是构建可运行 learning experiments，而不是把 v0.1 压在流程下面。

默认执行模型是 Harness + loop + skill：

```text
skill output
  -> project supplement
  -> implementation loop
  -> harness verification
  -> runbook and Feynman reflection
```

不要默认使用 multi-agent execution。只有用户要求，或高风险变更需要独立 review gate 时，才使用 external / independent review。

## Profiles

### Lightweight Learning Experiment Profile

用于 v0.1 和小型 learning experiments。

```text
context
  -> spec
  -> design diagram
  -> tasks
  -> implementation
  -> local test
  -> local run evidence
  -> Feynman reflection
```

必需 artifacts：

- `docs/01-specs/{experiment-id}.md`
- `docs/02-design/{experiment-id}.drawio`：当实验有 3 个以上 moving parts、数据关系、状态流转或 learning loop 时需要。
- `docs/03-tasks/{experiment-id}.md`
- `docs/04-runbooks/{experiment-id}-runbook.md`：当 commands 或 setup 变化时需要。
- Feynman reflection：可以放在 spec、task note、runbook 或 article draft 中。

### Standard Agent Engineer Profile

当 feature 跨多个 modules、API contracts、data model 或 UI/backend boundaries 时使用。

```text
context
  -> user / engineer stories
  -> spec
  -> design diagram
  -> design
  -> tasks
  -> implementation
  -> verification
  -> review
  -> knowledge capture
```

推荐 artifacts：

- `docs/00-project-rules/`：durable rules。
- `docs/01-specs/{feature-id}.md`
- `docs/02-design/{feature-id}.drawio`
- `docs/02-design/{feature-id}.drawio.png` 或 `.svg`
- `docs/02-design/{feature-id}-design.md`
- `docs/03-tasks/{feature-id}-tasks.md`
- `docs/03-tasks/{feature-id}-manifest.yaml`
- `docs/04-runbooks/{feature-id}-runbook.md`

### Enterprise SDD Profile

只有当项目真的需要完整 control-tower style lifecycle 时使用。

```text
requirements
  -> user stories
  -> spec
  -> architecture
  -> data flow
  -> data model
  -> design
  -> API / contract guide
  -> tasks
  -> implementation
  -> review
```

它不是默认流程。它适合 large platform slices、cross-team work、compliance-heavy work，或多个工具需要严格 handoff boundary 的场景。

## Minimum Gate Before Code

非平凡 implementation 开始前，当前 slice 必须能回答：

- 要解决什么问题？
- 用户或 engineer 是谁？
- Scope 内做什么？
- 明确不做什么？
- Happy path 必须发生什么？
- Validation、empty、error states 必须怎么表现？
- 需要什么 data shape 或 state contract？
- 哪个 module 拥有什么 responsibility？
- 本地怎么验收？
- 要捕获什么 evidence？
- 哪张 diagram 能解释 system、data model、flow 或 learning loop？

## Diagram Rule

当工作有 3 个以上 components、状态流转、数据关系或 handoff 时，从 visual model 开始。

默认 diagram format 是 draw.io：

- source：`docs/02-design/{feature-id}.drawio`
- exported preview：`docs/02-design/{feature-id}.drawio.png` 或 `.svg`

推荐 diagram types：

- architecture diagram
- sequence diagram
- ER diagram
- state diagram
- learning-loop diagram

Diagram 不是装饰。它应该帮助 engineer 在 implementation 前讲清楚设计。

## Grounding Rules

从上游 artifact 生成下游 docs 或 implementation 时：

- 验证已有 files、classes、methods、endpoints、tables、commands、configs 的说法。
- 未确认的说法标记 `[UNVERIFIED]`，不要写成事实。
- 不要因为上游文档写错了，就把错误继续传下去。
- Handoff 前检查内部矛盾。
- 不要用 “implementation will decide” 这种模糊句子推迟会影响实现的决定。

## Manifest Rule

Implementation loops consume manifests, not guesses。

多步骤 implementation 必须创建或更新 execution manifest，固定：

- spec path
- task path
- diagram path
- target branch
- relevant source files
- verification commands
- expected outputs
- constraints
- known risks

从这个模板开始：

```text
docs/03-tasks/execution-manifest-template.yaml
```

## External Prompt Rule

给外部工具的 prompts 应该是轻量指针。

推荐：

```text
Read AGENTS.md, the active spec, the active task list, and the runbook. Implement only task T003.
```

避免在 prompt 里复制整份 specs、designs 或 code。
如果工具能读 repo 文件，就让它读 source of truth。

只有当 prompt 可复用时，才存入：

```text
docs/07-prompts/
```
