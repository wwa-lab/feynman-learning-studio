# Feynman Learning Studio Constitution

## 目的

这份 constitution 定义 Feynman Learning Studio 的不可轻易改变的规则。

项目存在的目的，是帮助普通工程师通过 Spec Driven AI Development、可运行 learning experiments、本地 evidence 和 Feynman reflection，逐步成为 Agent Engineer。

## 核心原则

### I. Spec Is The Source Of Truth

每个非平凡变更都必须从 spec 或已接受的 task package 开始。

Spec 必须定义：

- goal
- scope
- non-goals
- acceptance criteria
- verification evidence
- open questions

Code、tests、runbooks 和 articles 都服务于 spec。AI 生成的 implementation 不能偷偷改变 spec。

### II. Runnable Experiment First

核心产品对象是可运行的 learning experiment。

每个有意义的 feature 都应该强化这个 loop：

```text
spec -> AI/Agent implementation -> local test -> local run -> explanation -> Feynman reflection
```

如果一个 feature 不能 test、run、explain 或 reflect，它就不能算完成。

### III. Evidence Before Explanation

AI explanation 必须建立在真实 evidence 上。

优先使用具体 artifact：

- test command
- local run command
- logs
- API examples
- UI 存在后的 screenshots
- error and fix records
- verification notes

不要把“AI 说能跑”当作证明。

### IV. Human Understanding Before Completion

AI summaries 不能代替人的理解。

每个完成的 learning experiment 都应该包含 Feynman reflection：工程师要用自己的话解释技术解决了什么、实现如何工作、哪里困惑、另一个工程师如何复现。

### V. Security And Validation By Default

所有外部输入默认不可信。

API、persistence、auth、file、external integration 工作都必须考虑：

- input validation
- SQL injection prevention
- secret handling
- safe error messages
- auditability
- dependency risk

Secrets 不应该进入 source code、prompts、run evidence 或 public docs。

## Governance

- Constitution 优先于低层 workflow 偏好。
- 修改 constitution 必须通过明确 docs change，并解释为什么原则需要变化。
- PR 应说明是否符合 constitution。
- 新增 complexity 必须结合当前阶段证明必要性。

**Version**: 0.1.0  
**Ratified**: 2026-06-08  
**Last Amended**: 2026-06-08
