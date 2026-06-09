# Feynman Learning Studio Agent Rules

## 项目使命

Feynman Learning Studio 是一个 Spec Driven AI Development 学习实践平台，目标是帮助普通工程师逐步成为 Agent Engineer。

这个项目用可运行的 learning experiment、本地 test/run evidence、AI/Agent 协作和 Feynman reflection，把已有工程经验转化成 AI 时代的交付能力。

IBM iSeries modernization 是第一个真实转型路径，但不是平台边界。

## Source Of Truth

做任何实现或文档变更时，按下面顺序读取和遵守规则：

1. 本仓库的 `AGENTS.md`。
2. `docs/00-project-rules/constitution.md`。
3. `docs/00-project-rules/project-rules.md`。
4. `docs/00-project-rules/development-standards.md`。
5. `docs/00-project-rules/design-standards.md`。
6. `docs/00-project-rules/backend-standards.md`。
7. `docs/00-project-rules/frontend-standards.md`。
8. `docs/00-project-rules/workflow.md`。
9. `docs/00-project-rules/sdd-pipeline.md`。
10. `docs/00-project-rules/agent-skill-routing.md`。
11. `docs/01-specs/` 下的 active spec，或当前 change package。
12. 全局 Codex / ECC instructions。

如果这些文档之间出现冲突，先停止实现，明确指出冲突，再继续。

## 默认工作方式

- 使用能安全完成任务的最轻 workflow。
- 小的文案修改、局部修复、低风险维护可以直接做。
- Feature work、architecture change、data model change、安全敏感变更、跨层改动，需要先给一个短 plan。
- 实现必须限制在当前 spec 或已接受 task 的 scope 内。
- 不要提前加入当前阶段不需要的 abstraction、service 或 infrastructure。

## 文档语言

- 这个 repo 是个人学习和实践项目。
- 项目文档默认使用中文。
- 已经稳定的技术名词、产品名、framework 名、API 名、class 名、file path、command、code identifier，可以保留英文，避免硬翻译。
- 示例：Spring Boot、MyBatis、H2、OpenAPI、Swagger、LearningTopic、LearningExperiment、CRUD、API、DTO、`mvn test`。

## Harness / Loop / Skill 工作模型

本项目默认不使用 multi-agent execution。

默认执行模型：

```text
user goal
  -> choose relevant skill
  -> preserve skill-native output
  -> add project supplement when needed
  -> run tight implementation loop
  -> generate understand-anything code map for non-trivial slices
  -> pass harness gates
  -> record evidence and learning notes
```

Skill 是主要 workflow surface：

- Requirements 和 stories：`req-to-user-story`、`user-story-to-spec`。
- Spec 和 design：`spec-to-architecture`、`architecture-to-design`、`drawio-skill`。
- Tasks 和 implementation：`design-to-tasks`、`tasks-to-implementation`、`tdd-workflow`。
- Review 和 verification：`review-doc-quality`、`review-code-against-design`、`security-review`、`verification-loop`。

每个 slice 使用 loop execution：

```text
plan small step
  -> make change
  -> run verification
  -> inspect evidence
  -> record issue/fix
  -> continue or stop
```

Harness gates 用来证明结果符合目标行为和项目风格：

- Spec gate：实现没有超出 accepted scope。
- Design gate：代码结构符合 approved design。
- Test gate：自动化测试通过。
- Run gate：本地 manual smoke 能跑通。
- Style gate：输出符合本 repo 的学习、文档和教学风格。
- Learning gate：runbook 记录 setup、verification、问题、fix、understand-anything 代码理解 evidence 和 Feynman reflection prompts。

对 AS/400 / IBM iSeries 背景的 Java 初学者，`understand-anything` 是理解 AI-generated code 的学习辅助工具。每个 non-trivial slice 完成实现和基础验证后，应优先运行 `/understand --language zh` 或等价流程，生成可视化 code map，用来解释 Spring Boot、MyBatis、Flyway、Swagger、test 和 package 之间的关系。

`.understand-anything/` 默认视为可再生成的本地索引，不自动提交。需要长期沉淀的内容应写入中文 reading guide、runbook、decision 或 article。

除非用户明确要求，不要启动或依赖多个 specialized agents。需要独立 second opinion 时，优先在 Codex 已完成代码、测试、文档和 evidence 后，请 Claude Code 做一个 bounded review。

## Codex / Claude Code 协作模型

当用户要求外部 review 时，默认分工如下：

- Codex 是 producer：写 specs、docs、code、tests、runbooks、manifests 和 local verification notes。
- Claude Code 可以作为 independent reviewer：review docs、code、security、spec alignment 和 verification evidence。
- 用户要求 non-trivial code change 的 independent review 时，不要把 Codex self-review 当作 Claude Code review 的替代品。
- 除非 reviewer 发现 blocking issue 且用户同意，不要要求 Claude Code 重写实现。
- 对较大的工作，review findings 应记录到 PR、task 文件，或 `docs/03-tasks/{feature-id}-review.md`。

建议 handoff：

```text
Codex implements from spec and manifest
  -> Codex runs local verification
  -> Claude Code reviews diff against spec/design/tasks
  -> Codex addresses accepted findings
  -> final verification evidence is recorded
```

## SDD 规则

- Specs drive implementation。不要让 AI-generated code 静默重新定义 scope。
- Skills 提供 workflow scaffolding，不是最终 definition of done。必须先把 skill-native output 作为独立 artifact 保留下来。
- 不要把 skill 原始输出和项目补充混在一起。需要补充时，用清楚的 supplement section 或 companion file 标明。
- 当 skill 默认输出不足以满足本 repo 的 SDD gates 时，补充缺失的 spec、architecture、design、data model、API contract、task、manifest、diagram、runbook、verification 或 review artifact。
- 每个 non-trivial feature 必须定义 goal、boundary、acceptance criteria、verification command 和 run evidence。
- 新的 non-trivial spec 从 `docs/01-specs/template.md` 开始。
- implementation task list 从 `docs/03-tasks/template.md` 开始。
- multi-step implementation handoff 使用 `docs/03-tasks/execution-manifest-template.yaml`。
- 每个 learning experiment 必须保留下面的 loop：

```text
spec -> AI/Agent implementation -> local test -> local run -> explanation -> Feynman reflection
```

- 没有 verification evidence，或没有明确说明为什么不能验证，不要标记为完成。
- non-trivial slice 应补一次 code understanding pass：用 `understand-anything` 生成或刷新 code map，再把关键阅读路径、AS/400 类比、疑问和结论记录到 runbook 或 reading guide。
- implementation loop 消费 manifest，不靠猜测推进。multi-step work 应固定 inputs、branch、constraints 和 verification commands。

## Engineering Rules

- Java target：JDK 17。
- Backend baseline：Spring Boot 3.x、Maven、MyBatis、Druid、H2 for tests。
- Database schema change 必须由 Flyway migration 管理。不要用临时 `schema.sql` 管理 application schema。
- Java coding baseline：Alibaba Java Coding Guidelines，并结合 Spring Boot 3 和 MyBatis 做项目内适配。
- Frontend baseline：开始 frontend 后使用 Vue 2.6 和 ElementUI。
- Frontend coding baseline：Vue style guide、eslint-plugin-vue Vue 2 rules，以及兼容的 Airbnb JavaScript。
- Design baseline：engineer workbench、evidence-first，非平凡 design 先有 draw.io diagram。
- 代码按 domain/feature 组织，不按 generic technical type 堆放。
- 在 controller boundary 验证所有 external input。
- SQL 通过 MyBatis 参数化执行，永远不要拼接 user input。
- API 返回统一 response envelope。
- server-side log 要有足够诊断信息，但不要把内部细节泄露给 API client。
- 文件保持 focused。优先使用多个 cohesive files，不写一个过大的文件。

## Testing Rules

- 新行为使用 TDD：先写会失败的 test，再实现。
- v0.1 必须能在不依赖 Docker 或外部数据库的情况下通过 H2 local tests。
- H2 tests 通过后，integration run 可以使用 MySQL / PostgreSQL。
- UI 出现后，critical user flows 应该补 E2E coverage。
- coverage 是 evidence，不是 vanity number。

## Security Rules

- 永远不要 hardcode secrets、credentials、tokens、passwords 或 private connection strings。
- `.env`、local credentials、generated logs、build outputs 不进 git。
- 验证 request body、query parameters、path variables 和 status transitions。
- fail safely，并返回 user-friendly error messages。
- 当变更涉及 API、persistence、auth 或 external integration code 时，commit 或 PR 前要做 security review。

## Git And GitHub Rules

- `main` 是 completed learning slices 唯一长期稳定分支。
- 使用 short-lived slice branches。Codex 创建 feature slice 时默认使用 `codex/<slice-id>`，除非用户另有要求。
- 默认不使用长期 `develop` branch 作为 integration path。如果 `develop-leo` 存在，把它视为 historical/local exploration，除非用户明确重新启用。
- slice branch merge 到 `main` 并确认 commits 已在 `main` 后，删除 local 和 remote slice branch。
- 使用 conventional commits：`feat:`、`fix:`、`docs:`、`test:`、`refactor:`、`chore:`、`ci:`、`perf:`。
- 没有用户明确批准，不要 push、merge、publish 或修改 GitHub repository settings。
- PR 必须包含 purpose、scope、verification、risks 和 follow-up notes。

## Completion Checklist

说“完成”之前，必须确认：

- 相关 tests 或 verification commands 已运行。
- 已 review `git diff`。
- 已检查 constitution compliance。
- behavior 或 workflow 改变时，docs / runbook / specs 已更新。
- 已考虑 security implications。
- 明确说明 remaining risks 或 skipped verification。
