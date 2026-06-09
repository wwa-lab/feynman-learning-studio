# SDD Pipeline

This document adapts lessons from `wwa-lab/Agentic-SDLC-Control-Tower` for Feynman Learning Studio.

Control Tower uses a full enterprise SDD chain. This project uses a lighter profile by default because the current goal is to build runnable learning experiments without burying v0.1 under process.

## Profiles

### Lightweight Learning Experiment Profile

Use for v0.1 and small learning experiments.

```text
context
  -> spec
  -> design diagram
  -> tasks
  -> implementation
  -> local test
  -> local run evidence
  -> Feynman reflection
```

Required artifacts:

- `docs/01-specs/{experiment-id}.md`
- `docs/02-design/{experiment-id}.drawio` for architecture, data flow, ERD, or learning-loop diagrams when the experiment has 3+ moving parts
- `docs/03-tasks/{experiment-id}.md`
- `docs/04-runbooks/{experiment-id}-runbook.md` when commands or setup change
- Feynman reflection inside the spec, task note, runbook, or article draft

### Standard Agent Engineer Profile

Use when a feature spans multiple modules, API contracts, data model, or UI/backend boundaries.

```text
context
  -> user / engineer stories
  -> spec
  -> design diagram
  -> design
  -> tasks
  -> implementation
  -> verification
  -> review
  -> knowledge capture
```

Recommended artifacts:

- `docs/00-project-rules/` for durable rules
- `docs/01-specs/{feature-id}.md`
- `docs/02-design/{feature-id}.drawio`
- `docs/02-design/{feature-id}.drawio.png` or `.svg` when export is available
- `docs/02-design/{feature-id}-design.md`
- `docs/03-tasks/{feature-id}-tasks.md`
- `docs/03-tasks/{feature-id}-manifest.yaml`
- `docs/04-runbooks/{feature-id}-runbook.md`

### Enterprise SDD Profile

Use only when the project genuinely needs a full control-tower style lifecycle.

```text
requirements
  -> user stories
  -> spec
  -> architecture
  -> data flow
  -> data model
  -> design
  -> API / contract guide
  -> tasks
  -> implementation
  -> review
```

This is intentionally not the default. It is useful for large platform slices, cross-team work, compliance-heavy work, or when multiple agents need strict handoff boundaries.

## Minimum Gate Before Code

Before non-trivial implementation starts, the current slice must answer:

- What problem is being solved?
- Who is the user or engineer?
- What is in scope?
- What is explicitly out of scope?
- What must happen in the happy path?
- What must happen in validation, empty, and error states?
- What data shape or state contract is needed?
- Which module owns which responsibility?
- How will acceptance be verified locally?
- What evidence will be captured?
- Which diagram explains the system, data model, flow, or learning loop?

## Diagram Rule

Start design with a visual model when the work has 3+ components, state transitions, data relationships, or agent handoffs.

Use draw.io as the default editable diagram format:

- source: `docs/02-design/{feature-id}.drawio`
- exported preview, when available: `docs/02-design/{feature-id}.drawio.png` or `.svg`

Recommended diagram types:

- architecture diagram for services, layers, and dependencies
- sequence diagram for request or agent handoff flow
- ER diagram for persistent data relationships
- state diagram for lifecycle/status transitions
- learning-loop diagram for spec -> AI -> test -> run -> reflection

The diagram is not decoration. It should help an engineer explain the design before implementation begins.

## Grounding Rules

When producing downstream docs or implementation from an upstream artifact:

- Verify claims about existing files, classes, methods, endpoints, tables, commands, and configs.
- Tag unresolved claims as `[UNVERIFIED]` instead of writing them as fact.
- Do not carry an upstream mistake forward just because it appears in an earlier document.
- Check for internal contradictions before handoff.
- Do not defer implementation-impacting decisions with vague phrases like "implementation will decide".

## Manifest Rule

Agents consume manifests, not guesses.

For multi-step implementation, create or update an execution manifest that pins:

- spec path
- task path
- diagram path
- target branch
- relevant source files
- verification commands
- expected outputs
- constraints
- known risks

Use `docs/03-tasks/execution-manifest-template.yaml` as the starting point.

## External Prompt Rule

Prompts for external tools should be lightweight pointers.

Prefer:

```text
Read AGENTS.md, the active spec, the active task list, and the runbook. Implement only task T003.
```

Avoid copying entire specs, designs, or code into prompts when the tool can read repository files directly.

Store durable prompts in `docs/07-prompts/` only when they are reusable.
