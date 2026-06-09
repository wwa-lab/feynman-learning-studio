# Tasks: [Feature Or Learning Experiment Name]

**Spec**: [Link to spec]  
**Owner**: [Name]  
**状态**: Draft

## Phase 0: Readiness

- [ ] 确认 spec 包含 goal、scope、non-goals、acceptance criteria 和 verification evidence。
- [ ] 确认 open questions 已回答，或明确作为 assumptions 接受。
- [ ] 确认 implementation slice 足够小，可以本地运行和验证。

## Phase 1: Exploration

- [ ] 检查当前 repo structure 和相关 files。
- [ ] 当 version-specific behavior 重要时，基于 primary docs 验证 framework/library 行为。
- [ ] 识别 boundaries、data model concerns 和 scope risks。
- [ ] 在 `docs/02-design/` 创建或更新 draw.io design diagram。
- [ ] 确认 diagram 能在 implementation 前解释系统。

## Phase 2: Test Design

- [ ] 为 P1 story 定义 unit/integration tests。
- [ ] 定义 validation 和 error-path tests。
- [ ] 检查 tests 是否能证明 acceptance criteria。

## Phase 3: Implementation

- [ ] 实现 story 所需 shared foundation。
- [ ] 实现 P1 story。
- [ ] 实现 validation 和 error handling。
- [ ] 更新 API docs 或 interface notes。

## Phase 4: Verification

- [ ] 运行 local test command：

```bash
[command]
```

- [ ] 运行 local run command：

```bash
[command]
```

- [ ] 在 runbook 或 task notes 中记录 evidence。
- [ ] 确认没有 generated artifacts、secrets 或 local logs 被纳入提交。

## Phase 5: Review

- [ ] 检查 correctness、spec drift 和 missing tests。
- [ ] 如涉及 API、persistence、auth 或外部集成，检查 validation、SQL safety、secret handling 和 error disclosure。
- [ ] 解决 findings，或记录 accepted residual risk。

## Phase 6: Knowledge Capture

- [ ] 更新 runbook。
- [ ] 如果 implementation 揭示真实 scope 或 acceptance change，更新 spec。
- [ ] 如果 boundaries、data relationships 或 flow 变化，更新 draw.io diagram。
- [ ] 添加 Feynman reflection notes。
- [ ] 有价值时添加 article 或 learning notes。

## Done Criteria

- [ ] Acceptance criteria 已满足。
- [ ] Required design diagram 存在，或 task 解释为什么不需要 diagram。
- [ ] Verification evidence 已记录。
- [ ] Learning experiment 有 Feynman reflection。
- [ ] Review/security findings 已处理或记录。
- [ ] Final summary 已可用于 PR。
