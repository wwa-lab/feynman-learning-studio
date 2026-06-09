# Design Standards

## Product Feel

Feynman Learning Studio is an engineer workbench, not a marketing site and not a generic admin demo.

The interface should feel:

- calm
- structured
- evidence-oriented
- readable for long work sessions
- serious enough for enterprise engineering
- approachable enough for learning and reflection

Avoid high-pressure command-center styling unless a future feature explicitly needs operational monitoring.

## Primary User Jobs

Design every screen around these jobs:

- understand what learning experiment is active
- see the spec, status, commands, and evidence
- run or reproduce the experiment
- inspect errors and fixes
- read AI explanations
- write Feynman reflection
- turn the result into reusable knowledge or article material

## Layout Principles

- Prefer workbench layouts over landing pages.
- Prefer tables, forms, detail panels, timelines, status strips, and evidence panels.
- Keep navigation predictable and stable.
- Put the core object, `LearningExperiment`, near the center of the experience.
- Make status and verification evidence visible without hunting.
- Avoid nested cards and decorative section cards.
- Keep visual hierarchy compact; do not use hero-scale typography inside tool surfaces.

## Visual Style

- Use a restrained professional palette with clear status colors.
- Do not let the UI become dominated by a single hue family.
- Avoid oversized gradients, decorative blobs, bokeh, and ornamental illustrations.
- Keep radii small: 4-8px unless a component library requires otherwise.
- Use spacing and light separators for grouping; avoid heavy borders everywhere.
- Use icons for obvious actions when available.

## Status Language

Status should be consistent across docs, backend, and frontend:

- `PLANNED`
- `RUNNING`
- `LOCAL_TEST_PASSED`
- `LOCAL_RUN_PASSED`
- `EXPLAINED_BY_AI`
- `ARTICLE_DRAFTED`
- `DONE`
- `ARCHIVED`

Status UI should make progression obvious.

## Draw.io Design Rule

At the beginning of design, create or update a draw.io diagram when the work has 3+ moving parts.

Use:

- architecture diagrams for components and layers
- sequence diagrams for request or agent handoff flow
- ER diagrams for persistence relationships
- state diagrams for lifecycle/status transitions
- learning-loop diagrams for spec -> AI -> test -> run -> reflection

Preferred files:

```text
docs/02-design/{feature-id}.drawio
docs/02-design/{feature-id}.drawio.png
```

If export is not available, the `.drawio` source is still required.

## Accessibility And Usability

- Text must not overflow controls or panels.
- Forms need clear validation states.
- Buttons should use clear labels or familiar icons.
- Tables need empty, loading, and error states.
- Keyboard and screen-reader basics should be considered once UI work begins.

## v0.2 Frontend Direction

The first frontend should be a workbench:

```text
left: learning topics
center: learning experiments list and status
right: experiment detail, spec, commands, evidence, AI summary, reflection
```

Do not build a landing page as the first usable experience.
