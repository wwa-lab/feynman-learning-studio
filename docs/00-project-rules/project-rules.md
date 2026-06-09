# Project Rules

## Product Identity

Feynman Learning Studio is a platform for helping engineers become Agent Engineers through Spec Driven AI Development.

The platform is not a generic blog, generic task tracker, or CRUD demo. Its center is the runnable learning experiment.

The project constitution is the highest-level rule document: `docs/00-project-rules/constitution.md`.

## Core Loop

Every meaningful product capability should strengthen this loop:

```text
existing engineering experience
  -> transformation topic
  -> learning experiment
  -> spec
  -> AI/Agent-assisted implementation
  -> local test
  -> local run evidence
  -> AI explanation
  -> Feynman reflection
  -> reusable knowledge or article draft
```

## Product Principles

- Evidence before explanation: run the code before asking AI to explain the result.
- Human understanding before completion: AI summaries do not replace Feynman reflection.
- Specs before implementation: specs define scope, constraints, and acceptance.
- Small runnable slices: each version must be independently testable and explainable.
- Domain experience matters: old-system, testing, ops, data, and business knowledge are assets to translate, not baggage to discard.

## Scope Discipline

v0.1 is intentionally small:

- Spring Boot 3.x backend.
- Learning Topic CRUD.
- Learning Experiment CRUD.
- Unified API response.
- Unified exception handling.
- H2 local tests.
- OpenAPI/Swagger.
- Local runbook.

Do not introduce Member, Team, TransformationPath, AgentWorkflow, RunEvidence, Redis, MQ, Nacos, Gateway, or frontend code in v0.1 unless the user explicitly changes scope.

## Naming

Use stable engineering names in code:

- `LearningTopic`
- `LearningExperiment`
- `LocalRun`
- `LocalTest`
- `Reflection`

Use stronger product language in docs/articles when helpful:

- transformation topic
- transformation experiment
- run evidence
- Feynman reflection
- Agent Engineer path

## Documentation

Project knowledge belongs in the repo:

- product and project rules: `docs/00-project-rules/`
- specs: `docs/01-specs/`
- architecture/design: `docs/02-design/`
- tasks/manifests: `docs/03-tasks/`
- runbooks: `docs/04-runbooks/`
- decisions: `docs/05-decisions/`
- articles/notes: `docs/06-articles/`

Do not create new top-level documentation folders without a reason.

Use the standard templates:

- spec template: `docs/01-specs/template.md`
- task template: `docs/03-tasks/template.md`
- execution manifest template: `docs/03-tasks/execution-manifest-template.yaml`

Use the SDD pipeline and routing guides:

- SDD pipeline: `docs/00-project-rules/sdd-pipeline.md`
- agent skill routing: `docs/00-project-rules/agent-skill-routing.md`

Use the detailed standards:

- design standards: `docs/00-project-rules/design-standards.md`
- backend standards: `docs/00-project-rules/backend-standards.md`
- frontend standards: `docs/00-project-rules/frontend-standards.md`

## External Actions

Networked tools are read-only by default. Inspecting GitHub, docs, or package registries is allowed. Pushing, publishing, modifying repository settings, opening paid jobs, posting content, or changing credentials requires explicit user approval.
