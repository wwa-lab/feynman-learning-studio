# Frontend Standards

## Coding Baseline

Use a project-tailored baseline:

```text
Vue official style guide
  + eslint-plugin-vue Vue 2 recommended rules
  + Airbnb JavaScript Style Guide where compatible
  + ElementUI workbench conventions
  + Feynman Learning Studio design standards
```

Vue and eslint-plugin-vue are the primary baseline for:

- single-file component structure
- component naming
- props and events
- template readability
- Vue 2 compatibility
- common Vue anti-pattern prevention

Airbnb JavaScript Style Guide is the secondary baseline for:

- JavaScript naming
- `const` / `let`
- object and array handling
- function style
- imports and modules
- equality and control flow
- whitespace and readability

ElementUI is the component baseline for v0.2 workbench UI:

- tables
- forms
- dialogs
- tags
- steps/status strips
- alerts
- pagination

Project design standards override generic UI preferences when there is tension. This is an engineer workbench, not a marketing site.

For v0.2, enforce the rules mainly through code review and targeted tests. Add ESLint/Prettier or equivalent tooling after the frontend skeleton exists.

Planned automation sequence:

1. Frontend unit tests.
2. ESLint with Vue 2 recommended/strongly-recommended rules.
3. Prettier or a stable formatter configuration.
4. Component tests for critical forms and status behavior.
5. Playwright E2E for critical user flows.

Do not block initial frontend scaffolding on the full automation stack.

## Stack

Planned frontend target:

- Vue 2.6
- ElementUI
- Axios or the project-standard HTTP client
- Vue Router when routing begins

## Component Organization

Use feature-oriented structure:

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

Rules:

- Route-level views live under `views/`.
- Reusable feature components live under `components/`.
- Shared generic components live under `shared/components/`.
- API clients live close to the feature unless shared by multiple features.
- Avoid putting feature-specific logic into global utilities.

## Naming

- Vue components use `PascalCase`.
- View components should end with `View`, for example `ExperimentWorkbenchView`.
- Dialog components should end with `Dialog`.
- Form components should end with `Form`.
- API functions use verb-noun names, for example `fetchExperimentPage`, `createTopic`, `updateExperimentStatus`.
- Constants use `UPPER_SNAKE_CASE`.
- Status labels and mappings should be centralized.

## Vue Component Rules

- Keep components focused; split when a component mixes unrelated concerns.
- Use props for parent-to-child data.
- Use events for child-to-parent changes.
- Do not mutate props directly.
- Keep computed properties pure.
- Keep watchers rare and justified.
- Keep template expressions simple; move logic into computed properties or methods.
- Use `scoped` styles by default unless intentionally styling shared/global surfaces.

## Product Direction

Build an operational learning workbench, not a landing page.

The first frontend should help users manage:

- learning topics
- learning experiments
- experiment status
- spec paths
- local test/run commands
- AI summaries
- Feynman reflections
- article draft paths

## Layout

Recommended v0.2 layout:

```text
left navigation: topics
center: experiment list / status
right panel or detail page: experiment detail and evidence
```

Use ElementUI components where they fit:

- `el-table` for lists
- `el-form` for create/edit flows
- `el-dialog` or routed detail screens for focused edits
- `el-tag` for status
- `el-steps` or compact custom status strips for progression
- `el-alert` for validation or run evidence warnings

## API Handling

- Use the backend API envelope consistently.
- Surface `errorMessage` to users when safe.
- Do not display raw stack traces or database errors.
- Keep frontend validation aligned with backend validation.
- Normalize pagination handling around `records`, `pageNo`, `pageSize`, and `total`.
- Keep HTTP error normalization in one place.
- Keep backend enum/status strings centralized in frontend constants.
- Do not let every component parse API envelopes differently.

## State And Data

- Keep data-fetching logic out of large components.
- Prefer small feature modules.
- Keep status mapping centralized.
- Avoid duplicating backend enum strings across many files.

## UX States

Every data view should handle:

- loading
- empty
- error
- success
- validation failure

Every mutation should provide clear feedback.

Form behavior:

- Show validation errors near the field.
- Disable submit while submitting.
- Preserve user input when validation fails.
- Confirm destructive actions such as archive/delete.
- Refresh list/detail state after successful mutation.

## Visual Rules

- Keep density useful for engineering work.
- Avoid hero sections and marketing copy.
- Avoid oversized cards, large rounded corners, decorative gradients, and ornamental backgrounds.
- Use icons for common actions when available.
- Ensure text fits controls on desktop and mobile widths.

## Testing

Once frontend tooling exists:

- unit test status mapping and API envelope handling
- unit test API envelope normalization
- component test important forms
- E2E test critical user flows when Playwright is introduced

Critical v0.2 user flows:

- create topic
- create experiment
- update experiment status
- view experiment evidence fields
- edit AI summary / reflection

## Review Checklist

Before handing frontend code to Claude Code review, confirm:

- [ ] Component structure follows Vue style guide intent.
- [ ] eslint-plugin-vue Vue 2 rules are respected where tooling exists.
- [ ] JavaScript follows Airbnb-style readability rules where compatible.
- [ ] ElementUI components are used consistently.
- [ ] API envelope handling is centralized.
- [ ] Status mapping is centralized.
- [ ] Loading, empty, error, success, and validation states are handled.
- [ ] UI follows `design-standards.md`.
