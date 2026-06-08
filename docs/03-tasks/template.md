# Tasks: [Feature Or Learning Experiment Name]

**Spec**: [Link to spec]  
**Owner**: [Name]  
**Status**: Draft

## Phase 0: Readiness

- [ ] Confirm the spec has goal, scope, non-goals, acceptance criteria, and verification evidence.
- [ ] Confirm open questions are answered or explicitly accepted as assumptions.
- [ ] Confirm the implementation slice is small enough to run locally.

## Phase 1: Exploration

- [ ] [Explorer] Inspect current repo structure and relevant files.
- [ ] [Docs Researcher] Verify framework/library behavior against primary docs when version-specific behavior matters.
- [ ] [Architect] Identify boundaries, data model concerns, and scope risks.
- [ ] [Architect] Create or update draw.io design diagram in `docs/02-design/`.
- [ ] [Reviewer] Confirm the diagram explains the system before implementation starts.

## Phase 2: Test Design

- [ ] [Engineer] Define unit/integration tests for P1 story.
- [ ] [Engineer] Define validation and error-path tests.
- [ ] [Reviewer] Check whether tests prove the acceptance criteria.

## Phase 3: Implementation

- [ ] [Engineer] Implement shared foundation needed by the story.
- [ ] [Engineer] Implement P1 story.
- [ ] [Engineer] Implement validation and error handling.
- [ ] [Engineer] Update API docs or interface notes.

## Phase 4: Verification

- [ ] Run local test command:

```bash
[command]
```

- [ ] Run local run command:

```bash
[command]
```

- [ ] Capture evidence in runbook or task notes.
- [ ] Confirm no generated artifacts, secrets, or local logs are included.

## Phase 5: Review

- [ ] [Reviewer] Review correctness, spec drift, and missing tests.
- [ ] [Security Reviewer] Review validation, SQL/persistence, secret handling, and error disclosure if relevant.
- [ ] [Orchestrator] Resolve findings or document accepted residual risk.

## Phase 6: Knowledge Capture

- [ ] Update runbook.
- [ ] Update spec if implementation revealed a real scope or acceptance change.
- [ ] Update draw.io diagram if implementation changed boundaries, data relationships, or flow.
- [ ] Add Feynman reflection notes.
- [ ] Add article or learning notes if useful.

## Done Criteria

- [ ] Acceptance criteria are met.
- [ ] Required design diagram exists or the task explains why no diagram was needed.
- [ ] Verification evidence is recorded.
- [ ] Feynman reflection exists for learning experiments.
- [ ] Reviewer/security findings are addressed or documented.
- [ ] Final summary is ready for PR.
