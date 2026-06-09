# Design Standards

## Product Feel

Feynman Learning Studio 是 engineer workbench，不是 marketing site，也不是 generic admin demo。

界面应该让人感觉：

- calm
- structured
- evidence-oriented
- 适合长时间阅读和操作
- 对 enterprise engineering 足够严肃
- 对学习和 reflection 足够友好

不要做高压 command-center 风格，除非未来 feature 明确需要 operational monitoring。

## Primary User Jobs

每个 screen 都要围绕这些任务设计：

- 理解当前 active learning experiment。
- 看到 spec、status、commands 和 evidence。
- 运行或复现实验。
- 检查 errors 和 fixes。
- 阅读 AI explanations。
- 写 Feynman reflection。
- 把结果整理成 reusable knowledge 或 article material。

## Layout Principles

- 优先使用 workbench layout，不做 landing page。
- 优先使用 tables、forms、detail panels、timelines、status strips、evidence panels。
- Navigation 要 predictable and stable。
- 核心对象 `LearningExperiment` 要靠近体验中心。
- Status 和 verification evidence 要一眼能看到。
- 避免 nested cards 和 decorative section cards。
- 保持 visual hierarchy 紧凑；不要在 tool surface 里使用 hero-scale typography。

## Visual Style

- 使用 restrained professional palette，并保留清晰 status colors。
- 不要让 UI 被单一 hue family 统治。
- 避免 oversized gradients、decorative blobs、bokeh、ornamental illustrations。
- Radius 保持小：4-8px，除非组件库要求不同。
- 用 spacing 和 light separators 分组，避免到处 heavy borders。
- 常见 action 优先使用 icons。

## Status Language

Status 在 docs、backend、frontend 中要保持一致。

候选 status：

- `PLANNED`
- `RUNNING`
- `LOCAL_TEST_PASSED`
- `LOCAL_RUN_PASSED`
- `EXPLAINED_BY_AI`
- `ARTICLE_DRAFTED`
- `DONE`
- `ARCHIVED`

Status UI 应该让 progression 明显可见。

## Draw.io Design Rule

当设计有 3 个以上 moving parts 时，先创建或更新 draw.io diagram。

使用：

- architecture diagram：components、layers、dependencies。
- sequence diagram：request flow 或 handoff flow。
- ER diagram：persistence relationships。
- state diagram：lifecycle/status transitions。
- learning-loop diagram：spec -> AI -> test -> run -> reflection。

推荐文件：

```text
docs/02-design/{feature-id}.drawio
docs/02-design/{feature-id}.drawio.png
```

如果暂时无法导出 preview，`.drawio` source 仍然必须存在。

## Accessibility And Usability

- Text 不应溢出 controls 或 panels。
- Forms 需要清楚 validation states。
- Buttons 使用清楚 labels 或 familiar icons。
- Tables 需要 empty、loading、error states。
- UI 开始后要考虑基本 keyboard 和 screen-reader support。

## v0.2 Frontend Direction

第一个 frontend 应该是 workbench：

```text
left: learning topics
center: learning experiments list and status
right: experiment detail, spec, commands, evidence, AI summary, reflection
```

不要把 landing page 当作第一个 usable experience。
