# Workflow

## 工作模式

使用能保护当前工作的最轻流程。不要为了流程而流程。

### Light Mode

适用于 wording changes、小文档修改、config cleanup、窄范围 bug fix。

流程：

```text
inspect -> edit -> verify -> summarize
```

### Standard SDD Mode

适用于普通 feature work。

流程：

```text
spec -> task list -> tests -> implementation -> local test -> local run -> review -> docs
```

多步骤工作需要 execution manifest：

```text
docs/03-tasks/execution-manifest-template.yaml
```

### Heavy Mode

适用于 architecture、security-sensitive work、cross-layer feature、data model change。

流程：

```text
proposal -> design -> tasks -> TDD -> implementation -> review -> verification -> archive
```

## Harness / Loop / Skill Rhythm

本项目默认使用 Harness + loop + skill，不默认使用 multi-agent execution。

默认流程：

```text
choose skill
  -> produce skill-native output
  -> add project supplement if the skill output is not enough
  -> run a small implementation loop
  -> verify through harness gates
  -> record evidence, issues, fixes, and learning notes
```

Step loop：

```text
plan one small step
  -> edit
  -> run the narrowest useful check
  -> inspect evidence
  -> record problems and fixes
  -> continue, adjust, or stop
```

Harness gates：

- Spec gate：变更必须符合已接受 scope 和 non-goals。
- Design gate：architecture、data model、API、package boundary 必须对齐。
- Test gate：自动化验证通过。
- Run gate：local manual smoke 可复现。
- Style gate：code/docs 符合本 repo 的 learning style。
- Learning gate：runbook 记录 setup、verification、踩坑、fixes、Feynman reflection prompts。

阶段、skill、gate 和 artifact 的映射见：

```text
docs/00-project-rules/agent-skill-routing.md
```

详细规则：

- Harness loop：`docs/00-project-rules/harness-loop.md`
- Harness gates：`docs/00-project-rules/harness-gates.md`

## Codex Producer / Claude Reviewer Flow

默认情况下，Codex 是 producer：

```text
Codex writes docs/code/tests/runbooks
  -> Codex records local verification evidence
  -> optional Claude Code review when requested or risky
  -> Codex applies accepted fixes
  -> final verification is recorded before PR/merge
```

只有在用户要求 independent review，或变更风险足够高时，才把 Claude Code review 放进默认流程。

适合 external review 的情况：

- non-trivial backend 或 frontend code。
- API contracts 或 persistence 变更。
- 会成为 implementation input 的 SDD docs。
- security-sensitive changes。
- 准备 merge 的 PR。

小文档修改、typo、local cleanup、普通 learning-loop work 不需要默认 Claude Code review。

Review 重点：

- spec alignment
- missing requirements
- behavioral bugs
- missing or weak tests
- security and validation gaps
- unclear verification evidence
- documentation drift

## Definition Of Ready

开始非平凡 implementation 前，当前 task 应该具备：

- Goal。
- Scope。
- Non-goals。
- Acceptance criteria。
- Verification command。
- Open questions，或明确说明无需进一步澄清。
- Multi-step implementation loop 的 execution manifest。

小任务可以隐式满足这些条件。

## TDD Path

新增行为默认走 TDD：

1. 先写或更新测试。
2. 运行测试，确认相关测试因预期原因失败。
3. 用最小实现让测试通过。
4. 在测试保持绿色的情况下 refactor。
5. 运行完整相关 verification command。

文档-only changes 应至少 proofread，并在可行时检查路径和链接。

## Verification Gates

最低验证要求：

- Docs/config changes：review diff，并检查引用路径存在。
- Backend changes：在 `backend` 下运行 `mvn test -Dspring.profiles.active=test`。
- Frontend changes：frontend 可用后运行 lint/test/build。
- Full-stack changes：backend tests、frontend checks、local smoke test。

PR 前：

- Review `git diff`。
- 确认没有 secrets 或 generated artifacts。
- 更新 runbook/spec/docs。
- 明确说明 skipped checks 和原因。

## Git Flow

本项目使用简单的 learning-slice branch strategy，不使用传统 Git Flow。

长期分支：

- `main`：唯一稳定 source of truth，只放完成的 learning slices。

短生命周期分支：

- `codex/<slice-id>`：Codex 创建的 feature 或 learning slice。
- `codex/docs-<topic>`：docs-only changes。
- `codex/fix-<topic>`：聚焦修复。
- `codex/chore-<topic>`：repo cleanup。

默认 branch pattern：

```text
main
  <- codex/<slice-id>
```

例子：

```text
codex/v0.2-run-evidence
codex/v0.3-reflection-core
codex/docs-harness-loop
codex/fix-flyway-migration
```

不默认使用长期 `develop` 分支。
如果 `develop-leo` 存在，把它当作历史或本地探索分支，除非用户明确决定恢复它。

Branch lifecycle：

```text
start from main
  -> create codex/<slice-id>
  -> complete harness loop
  -> push branch
  -> open PR
  -> merge into main
  -> confirm commits are in main
  -> delete local and remote slice branch
```

开始新 slice：

```bash
git switch main
git pull
git switch -c codex/<slice-id>
```

Merge 后清理：

```bash
git switch main
git pull
git branch -d codex/<slice-id>
git push origin --delete codex/<slice-id>
```

删除 branch 前必须确认：

- PR 已 merge，或用户确认不再需要。
- `main` 包含目标 commits。
- branch 上没有未合并的 local-only commit。

## Commit Messages

使用 conventional commits：

```text
feat: add learning topic API
fix: handle duplicate topic slug
docs: define project workflow
test: cover experiment status update
chore: configure codex agents
```

没有用户明确批准，不要 push、create PR、merge、publish 或修改 repository settings。

## GitHub Flow

Issues 用于 user-visible work 和 design questions。
PRs 用于 reviewable changes。

PR 应包含：

- Purpose。
- Scope。
- Verification。
- Screenshots 或 API examples。
- Risks and follow-ups。

后续可以考虑的 repository settings：

- merge 后自动 delete branch。
- 小 feature PR 使用 squash merge。
- CI 稳定后给 `main` 加 branch protection。
- 自动化测试可用后要求 PR review 和 status checks。
