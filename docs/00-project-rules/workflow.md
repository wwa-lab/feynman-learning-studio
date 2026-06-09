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

## Harness / Loop / Skill Rhythm

This project uses Harness + loop + skill as the default working model. Multi-agent execution is not the default.

Flow:

```text
choose skill
  -> produce skill-native output
  -> add project supplement if the skill output is not enough
  -> run a small implementation loop
  -> verify through harness gates
  -> record evidence, issues, fixes, and learning notes
```

Loop:

```text
plan one small step
  -> edit
  -> run the narrowest useful check
  -> inspect evidence
  -> record problems and fixes
  -> continue, adjust, or stop
```

Harness gates:

- Spec gate: the change matches accepted scope and non-goals.
- Design gate: architecture, data model, API, and package boundaries stay aligned.
- Test gate: automated verification passes.
- Run gate: local manual smoke is reproducible.
- Style gate: code/docs match this repository's learning style.
- Learning gate: runbook captures setup, verification,踩坑, fixes, and Feynman reflection prompts.

Use `docs/00-project-rules/agent-skill-routing.md` to map SDD stages to skills, harness gates, and expected artifacts.

Detailed rules:

- Harness loop: `docs/00-project-rules/harness-loop.md`
- Harness gates: `docs/00-project-rules/harness-gates.md`

## Codex Producer / Claude Reviewer Flow

Default tool split:

```text
Codex writes docs/code/tests/runbooks
  -> Codex records local verification evidence
  -> Claude Code performs independent review
  -> Codex applies accepted fixes
  -> final verification is recorded before PR/merge
```

Use this split only when the user asks for independent review or when a change is risky enough to justify it:

- non-trivial backend or frontend code
- changes to API contracts or persistence
- SDD docs that become implementation input
- security-sensitive changes
- PRs intended for merge

For tiny docs edits, typo fixes, local cleanup, and normal learning-loop work, Claude Code review is optional and not part of the default loop.

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
- Execution manifest for multi-step implementation loop.

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
