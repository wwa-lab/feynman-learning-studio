# Workflow

## Modes

Use the smallest workflow that protects the work.

### Light Mode

Use for wording changes, small docs edits, config cleanup, and narrow bug fixes.

Flow:

```text
inspect -> edit -> verify -> summarize
```

### Standard SDD Mode

Use for normal feature work.

Flow:

```text
spec -> task list -> tests -> implementation -> local test -> local run -> review -> docs
```

For multi-step work, add an execution manifest from `docs/03-tasks/execution-manifest-template.yaml`.

### Heavy Mode

Use for architecture, security-sensitive work, cross-layer features, or data model changes.

Flow:

```text
proposal -> design -> tasks -> TDD -> implementation -> review -> verification -> archive
```

## Agent Team Rhythm

For substantial work, use these roles:

1. `orchestrator` clarifies the goal, scope, and acceptance criteria.
2. `explorer` gathers repo evidence and relevant docs.
3. `spec-lead` updates or drafts the spec.
4. `architect` checks boundaries and evolution path.
5. `backend-engineer` or `frontend-engineer` implements.
6. `reviewer` checks correctness and missing tests.
7. `security-reviewer` checks sensitive surfaces.
8. `docs-researcher` verifies framework/API claims against primary sources.

The orchestrator decides the final path and reports only the useful synthesis to the user.

Use `docs/00-project-rules/agent-skill-routing.md` to map SDD stages to skills and roles.

## Codex Producer / Claude Reviewer Flow

Default tool split:

```text
Codex writes docs/code/tests/runbooks
  -> Codex records local verification evidence
  -> Claude Code performs independent review
  -> Codex applies accepted fixes
  -> final verification is recorded before PR/merge
```

Use this split for:

- non-trivial backend or frontend code
- changes to API contracts or persistence
- SDD docs that become implementation input
- security-sensitive changes
- PRs intended for merge

For tiny docs edits, typo fixes, or local cleanup, Claude Code review is optional.

Claude Code review should focus on:

- spec alignment
- missing requirements
- behavioral bugs
- missing or weak tests
- security and validation gaps
- unclear verification evidence
- documentation drift

## Definition Of Ready

Before implementation starts, the task should have:

- Goal.
- Scope.
- Non-goals.
- Acceptance criteria.
- Verification command.
- Open questions, or a clear decision that no clarification is needed.
- Execution manifest for multi-step or multi-agent work.

Small tasks can satisfy this implicitly.

## TDD Path

For new behavior:

1. Write or update tests first.
2. Run tests and confirm the relevant test fails for the expected reason.
3. Implement the smallest change to pass.
4. Refactor while tests stay green.
5. Run the full relevant verification command.

For documentation-only changes, proofread and run link/path checks when practical.

## Verification Gates

At minimum:

- Docs/config changes: review diff and check referenced paths exist.
- Backend changes: run `mvn test -Dspring.profiles.active=test` from `backend`.
- Frontend changes: run lint/test/build commands once available.
- Full-stack changes: run backend tests, frontend checks, and a local smoke test.

Before PR:

- Review `git diff`.
- Confirm no secrets or generated artifacts are included.
- Update runbook/spec/docs.
- State skipped checks and why.

## Git Flow

Recommended branch pattern:

```text
main
  <- codex/<short-change-name>
  <- develop-leo for local exploration when explicitly desired
```

Commit messages:

```text
feat: add learning topic API
fix: handle duplicate topic slug
docs: define project workflow
test: cover experiment status update
chore: configure codex agents
```

Do not push, create PRs, or change repository settings without explicit user approval.

## GitHub Flow

Use issues for user-visible work and design questions. Use PRs for reviewable changes.

PRs should include:

- Purpose.
- Scope.
- Verification.
- Screenshots or API examples when relevant.
- Risks and follow-ups.

Recommended repository settings to consider later:

- Enable delete branch on merge.
- Prefer squash merge for small feature PRs.
- Add branch protection for `main` after CI exists.
- Require PR review and status checks once tests are automated.
