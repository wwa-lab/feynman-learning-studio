# Frontend Standards

## Coding Baseline

Frontend 使用项目定制基线：

```text
Vue official style guide
  + eslint-plugin-vue Vue 2 recommended rules
  + Airbnb JavaScript Style Guide where compatible
  + ElementUI workbench conventions
  + Feynman Learning Studio design standards
```

Vue 和 eslint-plugin-vue 是主要基线，覆盖：

- single-file component structure
- component naming
- props and events
- template readability
- Vue 2 compatibility
- 常见 Vue anti-pattern prevention

Airbnb JavaScript Style Guide 是辅助基线，覆盖：

- JavaScript naming
- `const` / `let`
- object and array handling
- function style
- imports and modules
- equality and control flow
- whitespace and readability

ElementUI 是 v0.2 workbench UI 的组件基线：

- tables
- forms
- dialogs
- tags
- steps/status strips
- alerts
- pagination

当通用 UI 偏好和项目设计标准冲突时，以项目设计标准为准。这里是 engineer workbench，不是 marketing site。

v0.2 先通过 code review 和 targeted tests 执行规则。Frontend skeleton 稳定后，再加入 ESLint / Prettier 或等价 tooling。

计划中的自动化顺序：

1. Frontend unit tests。
2. ESLint with Vue 2 recommended/strongly-recommended rules。
3. Prettier 或稳定 formatter configuration。
4. Component tests for critical forms and status behavior。
5. Playwright E2E for critical user flows。

不要为了完整 automation stack 阻塞 initial frontend scaffolding。

## Stack

计划中的 frontend target：

- Vue 2.6
- ElementUI
- Axios 或项目统一 HTTP client
- Vue Router，当 routing 开始时引入

## Component Organization

使用 feature-oriented structure：

```text
frontend/src/
  api/
  shared/
    components/
    constants/
    utils/
  features/
    topic/
      components/
      views/
      api/
      model/
    experiment/
      components/
      views/
      api/
      model/
```

规则：

- Route-level views 放在 `views/`。
- Reusable feature components 放在 `components/`。
- Shared generic components 放在 `shared/components/`。
- API clients 尽量靠近 feature，除非多个 feature 共享。
- 不要把 feature-specific logic 塞进 global utilities。

## Naming

- Vue components 使用 `PascalCase`。
- View components 以 `View` 结尾，例如 `ExperimentWorkbenchView`。
- Dialog components 以 `Dialog` 结尾。
- Form components 以 `Form` 结尾。
- API functions 使用 verb-noun names，例如 `fetchExperimentPage`、`createTopic`、`updateExperimentStatus`。
- Constants 使用 `UPPER_SNAKE_CASE`。
- Status labels 和 mappings 要集中管理。

## Vue Component Rules

- Components 保持 focused；混入无关 concern 时拆分。
- Parent-to-child data 使用 props。
- Child-to-parent changes 使用 events。
- 不直接 mutate props。
- Computed properties 保持 pure。
- Watchers 少用，且要有理由。
- Template expressions 保持简单，复杂逻辑移到 computed 或 methods。
- 默认使用 `scoped` styles，除非明确在做 shared/global surfaces。

## Product Direction

构建 operational learning workbench，不构建 landing page。

第一个 frontend 应帮助用户管理：

- learning topics
- learning experiments
- experiment status
- spec paths
- local test/run commands
- AI summaries
- Feynman reflections
- article draft paths

## Layout

推荐 v0.2 layout：

```text
left navigation: topics
center: experiment list / status
right panel or detail page: experiment detail and evidence
```

合适时使用 ElementUI components：

- `el-table` for lists
- `el-form` for create/edit flows
- `el-dialog` 或 routed detail screens for focused edits
- `el-tag` for status
- `el-steps` 或 compact custom status strips for progression
- `el-alert` for validation or run evidence warnings

## API Handling

- 一致使用 backend API envelope。
- 安全时向用户展示 `errorMessage`。
- 不显示 raw stack traces 或 database errors。
- Frontend validation 要和 backend validation 对齐。
- Pagination 围绕 `records`、`pageNo`、`pageSize`、`total` 标准化。
- HTTP error normalization 放在一个地方。
- Backend enum/status strings 集中到 frontend constants。
- 不要让每个 component 各自解析 API envelope。

## State And Data

- Data-fetching logic 不要放进大型 components。
- 优先使用小的 feature modules。
- Status mapping 集中管理。
- 避免在很多文件里重复 backend enum strings。

## UX States

每个 data view 都要处理：

- loading
- empty
- error
- success
- validation failure

每个 mutation 都要有清楚 feedback。

Form behavior：

- Validation errors 显示在 field 附近。
- Submitting 时禁用 submit。
- Validation 失败时保留 user input。
- Archive/delete 等 destructive actions 需要确认。
- Mutation 成功后刷新 list/detail state。

## Visual Rules

- 保持适合工程工作的 useful density。
- 避免 hero sections 和 marketing copy。
- 避免 oversized cards、large rounded corners、decorative gradients、ornamental backgrounds。
- 常见 actions 优先使用 icons。
- 确保 desktop 和 mobile widths 下 text 都能放进 controls。

## Testing

Frontend tooling 存在后：

- unit test status mapping and API envelope handling
- unit test API envelope normalization
- component test important forms
- Playwright 引入后 E2E test critical user flows

v0.2 critical user flows：

- create topic
- create experiment
- update experiment status
- view experiment evidence fields
- edit AI summary / reflection

## Review Checklist

交给 Claude Code review 前，确认：

- [ ] Component structure 符合 Vue style guide intent。
- [ ] eslint-plugin-vue Vue 2 rules 在 tooling 可用时被遵守。
- [ ] JavaScript 符合 Airbnb-style readability rules。
- [ ] ElementUI components 使用一致。
- [ ] API envelope handling 集中处理。
- [ ] Status mapping 集中处理。
- [ ] Loading、empty、error、success、validation states 都处理。
- [ ] UI 符合 `design-standards.md`。
