# Skill Harness Routing

This document adapts the Control Tower skill-routing pattern for a Codex-centered Harness + loop + skill workflow.

## Rule

When a task clearly belongs to an SDD stage, use the matching skill instead of improvising a new workflow.

This project does not use multi-agent execution as the default. Treat skills as the primary workflow unit, and treat harness gates as the proof that the skill output and implementation are acceptable.

## Stage Routing

| Stage | Primary skill | Harness gate | Output |
|---|---|---|
| Raw idea or notes -> stories | `req-to-user-story` | clarity gate | user or engineer stories |
| Stories -> spec | `user-story-to-spec` | spec gate | implementation-facing spec |
| Spec -> architecture | `spec-to-architecture` | design gate | architecture notes |
| Architecture/spec -> diagram | `drawio-skill` | explanation gate | editable `.drawio` diagram |
| Architecture/spec -> design | `architecture-to-design` | design gate | design, data model, API guide |
| Design -> tasks | `design-to-tasks` | execution gate | phased task list |
| Tasks -> code | `tasks-to-implementation`, `tdd-workflow` | test gate | code, tests, docs |
| Doc quality review | `review-doc-quality` | doc gate | doc readiness report |
| Code vs design review | `review-code-against-design` | alignment gate | implementation alignment findings |
| Security review | `security-review` | security gate | security findings |
| Verification | `verification-loop` | evidence gate | verification evidence |

## Project Loop Roles

These are loop responsibilities, not separate agents:

- Scope owner: keep the current slice inside spec and non-goals.
- Evidence gatherer: inspect repo files, commands, logs, and framework docs when needed.
- Skill runner: apply the selected skill and preserve its native output.
- Implementer: make the smallest useful code or docs change.
- Harness checker: run tests, smoke, review diff, and check style.
- Learning recorder: update runbooks,踩坑 notes, and Feynman reflection prompts.

Use `drawio-skill` proactively when a design has 3+ components, state transitions, data relationships, or agent handoffs.

## Default Flow For v0.1

Use this flow for backend learning slices:

```text
spec skill
  -> architecture/design skill
  -> draw.io design diagram
  -> task skill
  -> TDD implementation loop
  -> verification-loop
  -> runbook and learning notes
```

The implementation still stays small: Spring Boot 3.x, MyBatis, H2 local tests, Topic/Experiment CRUD, unified response, unified exception handling.

## Handoff Notes

Each loop should leave evidence, not just opinions:

- files inspected and important facts found
- scope, non-goals, acceptance criteria, open questions
- boundary decisions and risks
- files changed, tests added, verification commands
- harness gate results
- security findings and residual risks when relevant
- primary docs or repo files used as sources when framework behavior matters

## Cross-Agent Compatibility

Tools that cannot load Codex skills directly should use this file as their routing map, then open the referenced repo docs and skill files manually.

Durable project facts belong in this repo. Temporary working notes can stay in the current thread.

## Codex And Claude Code Split

Codex is the default producer:

- create or update specs
- create tasks and manifests
- implement code
- add tests
- run local verification
- update runbooks and docs

Claude Code is optional independent reviewer when the user asks for it or a high-risk change needs a separate review gate:

- review docs for phase quality and traceability
- review code against specs, design, and tasks
- review security-sensitive changes
- challenge unclear harness evidence
- identify missing tests or behavioral regressions

When external review is requested, provide Claude Code with:

- `AGENTS.md`
- active spec
- task list
- execution manifest, if any
- relevant diff
- verification command output
- known risks or skipped checks
