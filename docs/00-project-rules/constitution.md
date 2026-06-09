# Feynman Learning Studio Constitution

## Purpose

This constitution defines the non-negotiable rules for Feynman Learning Studio.

The project exists to help ordinary engineers become Agent Engineers through Spec Driven AI Development, runnable learning experiments, local evidence, and Feynman reflection.

## Core Principles

### I. Spec Is The Source Of Truth

Every non-trivial change starts from a spec or an accepted task package.

The spec defines goal, scope, non-goals, acceptance criteria, verification evidence, and open questions. Code, tests, runbooks, and articles serve the spec. AI-generated implementation must not silently change the spec.

### II. Runnable Experiment First

The core product object is a runnable learning experiment.

Each meaningful feature must strengthen the loop:

```text
spec -> AI/Agent implementation -> local test -> local run -> explanation -> Feynman reflection
```

If a feature cannot be tested, run, explained, or reflected on, it is not complete.

### III. Evidence Before Explanation

AI explanations must be grounded in real evidence.

Prefer concrete artifacts:

- test command
- local run command
- logs
- API examples
- screenshots when UI exists
- error and fix records
- verification notes

Do not accept "AI says it works" as proof.

### IV. Human Understanding Before Completion

AI summaries do not replace human understanding.

Every completed learning experiment should include a Feynman reflection: the engineer explains in their own words what the technology solves, how the implementation works, what was confusing, and how another engineer can reproduce the result.

### V. Security And Validation By Default

All external input is untrusted.

API, persistence, auth, file, and external integration work must consider:

- input validation
- SQL injection prevention
- secret handling
- safe error messages
- auditability
- dependency risk

No secrets belong in source code, prompts, run evidence, or public docs.

## Governance

- This constitution supersedes lower-level workflow preferences.
- Amendments require an explicit docs change explaining why the principle changed.
- PRs should state whether they comply with the constitution.
- Complexity must be justified against the current stage.

**Version**: 0.1.0  
**Ratified**: 2026-06-08  
**Last Amended**: 2026-06-08
