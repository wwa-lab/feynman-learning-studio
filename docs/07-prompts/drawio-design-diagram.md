# Draw.io Design Diagram Prompt

Use this prompt when starting design for a feature or learning experiment.

```text
Create an editable draw.io design diagram for Feynman Learning Studio.

Read:
- AGENTS.md
- docs/00-project-rules/constitution.md
- docs/00-project-rules/sdd-pipeline.md
- the active spec
- the active task list, if any

Goal:
- Help engineers understand the design before implementation.
- Use draw.io XML as the editable source.

Output:
- docs/02-design/{feature-id}.drawio
- docs/02-design/{feature-id}.drawio.png if draw.io export is available

Choose the diagram type that best fits the work:
- architecture diagram
- sequence / flow diagram
- ER diagram
- state transition diagram
- learning-loop diagram

The diagram must show:
- actors or agents
- core components
- data stores or domain entities
- request / command / evidence flow
- verification or feedback loop

Keep it readable and focused. Do not turn the diagram into a full implementation plan.
```
