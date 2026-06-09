# Agent Skill Routing

This document adapts the Control Tower skill-routing pattern for Codex and other agents.

## Rule

When a task clearly belongs to an SDD stage, use the matching skill or agent role instead of improvising a new workflow.

## Stage Routing

| Stage | Primary skill / role | Output |
|---|---|---|
| Raw idea or notes -> stories | `req-to-user-story` / `spec-lead` | user or engineer stories |
| Stories -> spec | `user-story-to-spec` / `spec-lead` | implementation-facing spec |
| Spec -> architecture | `spec-to-architecture` / `architect` | architecture notes |
| Architecture/spec -> diagram | `drawio-skill` / `architect` | editable `.drawio` diagram |
| Architecture/spec -> design | `architecture-to-design` / `architect` | design, data model, API guide |
| Design -> tasks | `design-to-tasks` / `orchestrator` | phased task list |
| Tasks -> code | `tasks-to-implementation` / `backend-engineer` / `frontend-engineer` | code, tests, docs |
| Doc quality review | `review-doc-quality` / `reviewer` | doc readiness report |
| Code vs design review | `review-code-against-design` / `reviewer` | implementation alignment findings |
| Security review | `security-review` / `security-reviewer` | security findings |
| Verification | `verification-loop` / `orchestrator` | verification evidence |

## Project Agent Roles

The project-local Codex roles live under `.codex/agents/`:

- `explorer`: repo and evidence gathering
- `spec-lead`: spec and acceptance criteria
- `architect`: boundaries and evolution path
- `backend-engineer`: Spring Boot backend implementation
- `frontend-engineer`: Vue2 + ElementUI implementation
- `reviewer`: correctness and spec drift review
- `security-reviewer`: security-sensitive review
- `docs-researcher`: framework/API documentation verification

Use `drawio-skill` proactively when a design has 3+ components, state transitions, data relationships, or agent handoffs.

## Default Flow For v0.1

Use this flow for the first backend slice:

```text
spec-lead
  -> architect
  -> draw.io design diagram
  -> backend-engineer
  -> reviewer
  -> security-reviewer
  -> orchestrator verification
```

The implementation still stays small: Spring Boot 3.x, MyBatis, H2 local tests, Topic/Experiment CRUD, unified response, unified exception handling.

## Handoff Notes

Each role should leave evidence, not just opinions:

- `explorer`: files inspected and important facts found
- `spec-lead`: scope, non-goals, acceptance criteria, open questions
- `architect`: boundary decisions and risks
- `engineer`: files changed, tests added, verification commands
- `reviewer`: findings by severity
- `security-reviewer`: security findings and residual risks
- `docs-researcher`: primary docs or repo files used as sources

## Cross-Agent Compatibility

Agents that cannot load Codex skills directly should use this file as their routing map, then open the referenced repo docs and skill files manually.

Durable project facts belong in this repo. Temporary working notes can stay in the current agent thread.

## Codex And Claude Code Split

Codex is the default producer:

- create or update specs
- create tasks and manifests
- implement code
- add tests
- run local verification
- update runbooks and docs

Claude Code is the default independent reviewer:

- review docs for phase quality and traceability
- review code against specs, design, and tasks
- review security-sensitive changes
- challenge unclear verification evidence
- identify missing tests or behavioral regressions

For review, provide Claude Code with:

- `AGENTS.md`
- active spec
- task list
- execution manifest, if any
- relevant diff
- verification command output
- known risks or skipped checks
