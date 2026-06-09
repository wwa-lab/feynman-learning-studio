# Spec: [Feature Or Learning Experiment Name]

**状态**: Draft
**创建日期**: YYYY-MM-DD
**Owner**: [Name]
**相关主题**: [Learning Topic / Transformation Topic]
**目标阶段**: [v0.1 / v0.2 / ...]

## 1. 目标

[用普通语言说明这次 transformation 或 product goal。]

## 2. 既有工程经验

[这个 experiment 要把哪些已有工程经验翻译到 AI-era delivery？]

例子：

- IBM iSeries program + database file + job flow。
- Traditional Java CRUD。
- Testing workflow。
- Operations 或 debugging workflow。
- Business analysis 或 requirement writing。

## 3. 问题陈述

[这个 spec 要解决什么 gap、confusion 或 capability？]

## 4. Scope

范围内：

- [Capability 1]
- [Capability 2]
- [Capability 3]

范围外：

- [Explicit non-goal 1]
- [Explicit non-goal 2]

## 5. User / Engineer Stories

### Story 1: [Title] (Priority: P1)

作为 [role]，我希望 [action]，以便 [benefit]。

**Independent Test**: [这个 story 如何独立验证。]

**Acceptance Scenarios**:

1. Given [state], when [action], then [outcome].
2. Given [state], when [action], then [outcome].

### Story 2: [Title] (Priority: P2)

作为 [role]，我希望 [action]，以便 [benefit]。

**Independent Test**: [这个 story 如何独立验证。]

**Acceptance Scenarios**:

1. Given [state], when [action], then [outcome].

## 6. Functional Requirements

- **FR-001**: 系统 MUST [specific behavior]。
- **FR-002**: 系统 MUST [specific behavior]。
- **FR-003**: 系统 MUST [specific behavior]。

## 7. Data / Domain Model

[列出关键 entities 和 relationships，不要过度设计 implementation。]

- **Entity A**: [用途和关键字段。]
- **Entity B**: [用途和关键字段。]

## 8. API / Interface Contract

[描述 endpoints、commands、UI actions 或 workflow interfaces。]

```text
METHOD /path
```

Expected response shape：

```json
{
  "success": true,
  "data": {},
  "errorCode": null,
  "errorMessage": null
}
```

## 9. Design Diagram

当 experiment 或 feature 有 3 个以上 components、状态流转、数据关系或 handoff 时，必须有 diagram。

Diagram source：

```text
docs/02-design/[feature-id].drawio
```

Preview export，可用时：

```text
docs/02-design/[feature-id].drawio.png
```

Diagram type：

- [ ] Architecture
- [ ] Sequence / flow
- [ ] ERD
- [ ] State transition
- [ ] Learning loop

Diagram 必须讲清楚：

- [Component / actor / data relationship 1]
- [Component / actor / data relationship 2]
- [Important flow or transition]

## 10. Validation And Error Handling

- [Validation rule]
- [Business error]
- [System error handling expectation]

## 11. AI / Skill Role

[描述 AI 或 skill 如何帮助。默认围绕 Harness + loop + skill，不默认 multi-agent。]

- Skill selection:
- Skill-native output:
- Project supplement:
- Implementation loop:
- Harness gates:

## 12. Verification Evidence

Required local test：

```bash
[command]
```

Required local run：

```bash
[command]
```

Evidence to capture：

- [ ] Test output。
- [ ] Local run output。
- [ ] API example 或 screenshot。
- [ ] Error/fix note，如果发生 failure。
- [ ] Runbook update。

## 13. Feynman Reflection Questions

- 这个 technology 或 feature 解决什么问题？
- 用我自己的话说，implementation 是怎么工作的？
- 不看代码，我能不能解释 design diagram？
- 最开始让我困惑的部分是什么？
- 什么 evidence 证明它能工作？
- 另一个 engineer 如何复现？
- AI 帮了什么？我仍然需要自己验证什么？

## 14. Open Questions

- [Question 1]
- [Question 2]
