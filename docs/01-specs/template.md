# Spec: [Feature Or Learning Experiment Name]

**Status**: Draft  
**Created**: YYYY-MM-DD  
**Owner**: [Name]  
**Related Topic**: [Learning Topic / Transformation Topic]  
**Target Stage**: [v0.1 / v0.2 / ...]

## 1. Goal

[Describe the transformation or product goal in plain language.]

## 2. Existing Engineering Experience

[What prior engineering experience should this experiment translate?]

Examples:

- IBM iSeries program + database file + job flow.
- Traditional Java CRUD.
- Testing workflow.
- Operations or debugging workflow.
- Business analysis or requirement writing.

## 3. Problem Statement

[What gap, confusion, or capability does this spec address?]

## 4. Scope

In scope:

- [Capability 1]
- [Capability 2]
- [Capability 3]

Out of scope:

- [Explicit non-goal 1]
- [Explicit non-goal 2]

## 5. User / Engineer Stories

### Story 1: [Title] (Priority: P1)

As a [role], I want to [action], so that [benefit].

**Independent Test**: [How this story can be verified by itself.]

**Acceptance Scenarios**:

1. Given [state], when [action], then [outcome].
2. Given [state], when [action], then [outcome].

### Story 2: [Title] (Priority: P2)

As a [role], I want to [action], so that [benefit].

**Independent Test**: [How this story can be verified by itself.]

**Acceptance Scenarios**:

1. Given [state], when [action], then [outcome].

## 6. Functional Requirements

- **FR-001**: The system MUST [specific behavior].
- **FR-002**: The system MUST [specific behavior].
- **FR-003**: The system MUST [specific behavior].

## 7. Data / Domain Model

[List key entities and relationships without over-designing implementation.]

- **Entity A**: [Purpose and key fields.]
- **Entity B**: [Purpose and key fields.]

## 8. API / Interface Contract

[Describe endpoints, commands, UI actions, or workflow interfaces.]

```text
METHOD /path
```

Expected response shape:

```json
{
  "success": true,
  "data": {},
  "errorCode": null,
  "errorMessage": null
}
```

## 9. Design Diagram

Required when the experiment or feature has 3+ components, state transitions, data relationships, or agent handoffs.

Diagram source:

```text
docs/02-design/[feature-id].drawio
```

Preview export, when available:

```text
docs/02-design/[feature-id].drawio.png
```

Diagram type:

- [ ] Architecture
- [ ] Sequence / flow
- [ ] ERD
- [ ] State transition
- [ ] Learning loop

What the diagram must make clear:

- [Component / actor / data relationship 1]
- [Component / actor / data relationship 2]
- [Important flow or transition]

## 10. Validation And Error Handling

- [Validation rule]
- [Business error]
- [System error handling expectation]

## 11. AI / Agent Role

[Describe how AI or sub-agents may help.]

- Explorer:
- Spec Lead:
- Engineer:
- Reviewer:
- Security Reviewer:
- Docs Researcher:

## 12. Verification Evidence

Required local test:

```bash
[command]
```

Required local run:

```bash
[command]
```

Evidence to capture:

- [ ] Test output.
- [ ] Local run output.
- [ ] API example or screenshot.
- [ ] Error/fix note if failures occur.
- [ ] Runbook update.

## 13. Feynman Reflection Questions

- What problem does this technology or feature solve?
- How does the implementation work, in my own words?
- Can I explain the design diagram without reading the code?
- What part was initially confusing?
- What evidence proves it works?
- How can another engineer reproduce it?
- How did AI help, and what did I still need to verify?

## 14. Open Questions

- [Question 1]
- [Question 2]
