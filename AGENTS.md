# Feynman Learning Studio Agent Rules

## Project Mission

Feynman Learning Studio is a Spec Driven AI Development practice platform for helping ordinary engineers become Agent Engineers.

The product uses runnable learning experiments, local test/run evidence, AI/Agent collaboration, and Feynman reflection to turn existing engineering experience into AI-era delivery capability.

IBM iSeries modernization is the first real transformation path, not the platform boundary.

## Source Of Truth

Follow these project rules in this order:

1. `AGENTS.md` in this repository.
2. `docs/00-project-rules/constitution.md`.
3. `docs/00-project-rules/project-rules.md`.
4. `docs/00-project-rules/development-standards.md`.
5. `docs/00-project-rules/design-standards.md`.
6. `docs/00-project-rules/backend-standards.md`.
7. `docs/00-project-rules/frontend-standards.md`.
8. `docs/00-project-rules/workflow.md`.
9. `docs/00-project-rules/sdd-pipeline.md`.
10. `docs/00-project-rules/agent-skill-routing.md`.
11. Active specs under `docs/01-specs/` or the current change package.
12. Global Codex/ECC instructions.

When documents disagree, stop and surface the conflict before implementing.

## Default Work Style

- Use the lightest workflow that safely fits the task.
- Small edits, copy changes, and local fixes can be done directly.
- Feature work, architecture changes, data model changes, security-sensitive work, and cross-layer changes require a short plan first.
- Keep implementation scoped to the current spec or accepted task.
- Do not add speculative abstractions, services, or infrastructure before the current stage needs them.

## Agent Team Model

Work as an agent team when the task benefits from multiple perspectives:

- `orchestrator`: owns the current goal, scope, sequencing, and final synthesis.
- `explorer`: gathers evidence from code, docs, logs, and external docs.
- `spec-lead`: turns fuzzy goals into specs, acceptance criteria, and open questions.
- `architect`: reviews boundaries, data model, module structure, and evolution path.
- `backend-engineer`: implements Spring Boot, MyBatis, persistence, APIs, tests, and runbooks.
- `frontend-engineer`: implements Vue2 + ElementUI workflow screens when frontend work begins.
- `reviewer`: reviews correctness, maintainability, and missing tests.
- `security-reviewer`: checks input validation, secret handling, SQL injection, auth, and data exposure.
- `docs-researcher`: verifies framework/API behavior against primary docs when current behavior matters.

Prefer parallel exploration/review when work is independent. The orchestrator remains responsible for the final decision and user-facing answer.

## Codex / Claude Code Collaboration Model

Default division of labor:

- Codex is the producer: write specs, docs, code, tests, runbooks, manifests, and local verification notes.
- Claude Code is the independent reviewer: review docs, code, security, spec alignment, and verification evidence before merge.
- Do not treat a Codex self-review as a replacement for Claude Code review on non-trivial code changes.
- Do not ask Claude Code to rewrite the implementation during review unless the reviewer finds a blocking issue and the user agrees.
- Review findings should be recorded in the PR, task file, or `docs/03-tasks/{feature-id}-review.md` for larger work.

Suggested handoff:

```text
Codex implements from spec and manifest
  -> Codex runs local verification
  -> Claude Code reviews diff against spec/design/tasks
  -> Codex addresses accepted findings
  -> final verification evidence is recorded
```

## SDD Rules

- Specs drive implementation. Do not let AI-generated code redefine scope silently.
- Every non-trivial feature must define goal, boundary, acceptance criteria, verification command, and run evidence.
- Start new non-trivial specs from `docs/01-specs/template.md`.
- Start implementation task lists from `docs/03-tasks/template.md`.
- Use `docs/03-tasks/execution-manifest-template.yaml` for multi-step implementation handoff.
- Every learning experiment must preserve the loop:

```text
spec -> AI/Agent implementation -> local test -> local run -> explanation -> Feynman reflection
```

- Do not mark work complete without verification evidence or a clear reason why verification could not run.
- Agents consume manifests, not guesses: multi-step work should pin inputs, branch, constraints, and verification commands.

## Engineering Rules

- Java target: JDK 17.
- Backend baseline: Spring Boot 3.x, Maven, MyBatis, Druid, H2 for tests.
- Java coding baseline: Alibaba Java Coding Guidelines, adapted for Spring Boot 3 and MyBatis.
- Frontend baseline: Vue 2.6 and ElementUI when frontend starts.
- Frontend coding baseline: Vue style guide, eslint-plugin-vue Vue 2 rules, and Airbnb JavaScript where compatible.
- Design baseline: engineer workbench, evidence-first, draw.io diagram before implementation for non-trivial design.
- Organize code by domain/feature, not by generic technical type.
- Validate all external input at controller boundaries.
- Use parameterized SQL through MyBatis; never concatenate user input into SQL.
- Return consistent API envelopes.
- Log enough server-side context for diagnosis; do not leak internals to API clients.
- Keep files focused. Prefer several cohesive files over one oversized file.

## Testing Rules

- Use TDD for new behavior: write the failing test first, then implement.
- v0.1 must pass H2 local tests without Docker or external databases.
- Integration runs may use MySQL/PostgreSQL after H2 tests pass.
- Critical user flows should get E2E coverage once the UI exists.
- Treat coverage as meaningful evidence, not a vanity number.

## Security Rules

- Never hardcode secrets, credentials, tokens, passwords, or private connection strings.
- Keep `.env`, local credentials, generated logs, and build outputs out of git.
- Validate request bodies, query parameters, path variables, and status transitions.
- Fail safely and return user-friendly error messages.
- Run a security review before commit or PR when API, persistence, auth, or external integration code changes.

## Git And GitHub Rules

- Work on feature branches. Use `codex/` for Codex-created branches unless the user requests otherwise.
- Use conventional commits: `feat:`, `fix:`, `docs:`, `test:`, `refactor:`, `chore:`, `ci:`, `perf:`.
- Do not push, merge, publish, or change GitHub repository settings without explicit user approval.
- PRs must include purpose, scope, verification, risks, and follow-up notes.

## Completion Checklist

Before saying work is complete:

- Relevant tests or verification commands were run.
- `git diff` was reviewed.
- Constitution compliance was checked.
- Docs/runbook/specs were updated when behavior or workflow changed.
- Security implications were considered.
- Remaining risks or skipped verification are stated plainly.
