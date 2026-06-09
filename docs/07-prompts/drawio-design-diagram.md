# Draw.io Design Diagram Prompt

当开始一个 feature 或 learning experiment 的设计，并且需要生成 draw.io diagram 时，使用这个 prompt。

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

说明：

- Prompt 本体保留英文，方便外部工具稳定执行。
- 输出文件必须进入 `docs/02-design/`。
- Diagram 是 design evidence，不是装饰图。
