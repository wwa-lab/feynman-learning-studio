# Development Standards

This is the summary entry point. Detailed standards live in:

- `docs/00-project-rules/design-standards.md`
- `docs/00-project-rules/backend-standards.md`
- `docs/00-project-rules/frontend-standards.md`

## Backend Standards

Backend code follows a tailored baseline:

```text
Alibaba Java Coding Guidelines
  + Spring Boot 3 engineering practice
  + MyBatis / SQL safety rules
```

Target stack for the first implementation path:

- JDK 17.
- Spring Boot 3.x.
- Maven.
- MyBatis.
- Druid.
- H2 for local test.
- MySQL or PostgreSQL for local integration.
- JUnit 5.
- OpenAPI/Swagger.

## Package Organization

Organize by feature/domain:

```text
backend/src/main/java/com/feynman/learningstudio/
  common/
    api/
    exception/
    validation/
  topic/
    controller/
    service/
    mapper/
    domain/
    dto/
  experiment/
    controller/
    service/
    mapper/
    domain/
    dto/
```

## API Standards

Use resource-oriented endpoints:

```text
POST   /api/topics
GET    /api/topics/{id}
GET    /api/topics
PUT    /api/topics/{id}
DELETE /api/topics/{id}

POST   /api/experiments
GET    /api/experiments/{id}
GET    /api/experiments
PUT    /api/experiments/{id}
PATCH  /api/experiments/{id}/status
DELETE /api/experiments/{id}
```

Return a consistent envelope:

```json
{
  "success": true,
  "data": {},
  "errorCode": null,
  "errorMessage": null
}
```

Pagination data should include:

```json
{
  "records": [],
  "pageNo": 1,
  "pageSize": 10,
  "total": 0
}
```

## Validation

- Validate all request bodies with Jakarta Bean Validation.
- Validate path variables and query parameters.
- Validate enum/status transitions in the service layer.
- Reject invalid slugs. Topic slugs should use lowercase letters, numbers, and hyphens.
- Return stable business error codes for predictable failures.

## Persistence

- Keep SQL in MyBatis mapper XML or mapper annotations.
- Use parameterized queries only.
- Do not concatenate user-controlled values into SQL.
- Manage database schema through Flyway migrations.
- Keep H2 migration behavior close to the integration database migration behavior.
- Document H2/MySQL/PostgreSQL differences in the runbook.

## Error Handling

- Use a global exception handler.
- Convert validation, not-found, conflict, database, and unexpected errors into the API envelope.
- Log detailed server-side context.
- Do not expose SQL, stack traces, connection strings, or internal class names in API error messages.

## Testing

Required for v0.1:

- Unit tests for service validation and status behavior.
- API integration tests for Topic and Experiment endpoints.
- H2-backed tests that do not require Docker or external databases.
- Tests for validation errors, not-found errors, duplicate slug, and invalid topic references.

Preferred command:

```bash
cd backend
mvn test -Dspring.profiles.active=test
```

## Frontend Standards

Frontend code follows a tailored baseline:

```text
Vue official style guide
  + eslint-plugin-vue Vue 2 recommended rules
  + Airbnb JavaScript Style Guide where compatible
  + ElementUI workbench conventions
```

When frontend starts:

- Use Vue 2.6 + ElementUI.
- Build workbench screens, not marketing pages.
- Favor dense, clear, operational UI.
- Keep form validation aligned with backend validation.
- Use API envelopes consistently.

## Documentation Standards

Every non-trivial implementation should update at least one of:

- active spec
- runbook
- task checklist
- decision record
- article notes

Docs should record what changed, how to run it, how it was verified, and what remains uncertain.
