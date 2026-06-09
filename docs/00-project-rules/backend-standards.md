# Backend Standards

## Coding Baseline

Use a project-tailored baseline:

```text
Alibaba Java Coding Guidelines
  + Spring Boot 3 engineering practice
  + MyBatis / SQL safety rules
  + automated formatting and static checks over time
```

Alibaba Java Coding Guidelines are the primary human-readable baseline for:

- naming
- constants
- collection usage
- exception handling
- logging
- unit testing discipline
- security awareness
- MySQL / SQL conventions
- project structure judgment

Spring Boot project practice is the baseline for:

- controller/service/mapper responsibilities
- dependency injection
- configuration profiles
- validation and global exception handling
- actuator and OpenAPI integration
- test slices and integration tests

MyBatis-specific rules are mandatory for persistence:

- parameterized SQL only
- no user-input string concatenation
- mapper methods must match XML statements
- SQL differences between H2 and integration databases must be documented

For v0.1, these rules are enforced by review and tests first. Static-analysis tooling can be added after the backend skeleton exists.

Planned automation sequence:

1. Maven test with H2 profile.
2. Formatter, such as Spotless or Spring Java Format.
3. Checkstyle for basic formatting/style consistency.
4. SpotBugs for bug patterns.
5. PMD / P3C rules for Alibaba guideline coverage where practical.

Do not block v0.1 scaffolding on full static-analysis setup.

## Stack

v0.1 backend target:

- JDK 17
- Spring Boot 3.x
- Maven
- MyBatis
- Druid
- Flyway
- H2 for local test
- MySQL or PostgreSQL for local integration
- JUnit 5
- OpenAPI / Swagger

## Module Structure

Use package-by-feature:

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

Do not use a flat `controller/`, `service/`, `mapper/` package for all domains.

## Naming Rules

- Class names use `UpperCamelCase`.
- Method and field names use `lowerCamelCase`.
- Constants use `UPPER_SNAKE_CASE`.
- DTOs should make request/response direction clear, for example `CreateTopicRequest`, `TopicResponse`, `TopicQuery`.
- Business exceptions should be named after the failure concept, for example `TopicNotFoundException`.
- Mapper XML namespace must match the mapper interface fully qualified name.

## API Contract

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

All endpoints return the same envelope:

```json
{
  "success": true,
  "data": {},
  "errorCode": null,
  "errorMessage": null
}
```

Pagination data:

```json
{
  "records": [],
  "pageNo": 1,
  "pageSize": 10,
  "total": 0
}
```

## Validation

- Validate request bodies with Jakarta Bean Validation.
- Validate path variables and query parameters.
- Validate status values and status transitions in the service layer.
- Validate topic slug format: lowercase letters, numbers, and hyphens.
- Validate experiment references to existing topics.

## Error Handling

Use a global exception handler.

Required error categories:

- validation failure
- topic not found
- experiment not found
- duplicate slug
- invalid topic reference
- database access failure
- unexpected system error

API errors must be stable and user-friendly. Do not expose SQL, stack traces, class names, connection strings, or local file paths.

Logging and exception rules:

- Do not swallow exceptions silently.
- Do not catch broad exceptions unless converting them at a boundary.
- Log unexpected exceptions once at the boundary with context.
- Do not log secrets, tokens, passwords, or full database URLs.

## Persistence

- Keep SQL in MyBatis mapper XML or mapper annotations.
- Use parameterized queries only.
- Never concatenate user input into SQL.
- Manage database schema with Flyway migrations under `backend/src/main/resources/db/migration/`.
- Do not use ad hoc `schema.sql` for application schema management.
- Keep H2 migrations close to the integration database migrations.
- Document H2 and MySQL/PostgreSQL differences in the runbook.
- Avoid `SELECT *`; select explicit columns.
- Keep pagination deterministic with explicit ordering.
- Use unique constraints for unique business keys such as topic slug.
- Add indexes when query patterns justify them; do not add speculative indexes.

## Database Migration

- Use Flyway for all schema changes.
- Name migrations with Flyway versioned migration format, for example `V1__create_learning_core_tables.sql`.
- Keep migrations append-only after they have been shared or merged.
- Prefer explicit table, column, constraint, and index names.
- Do not rewrite an existing applied migration to hide a design change; add a new migration.
- For learning slices, explain important migration decisions in the runbook.

## Transactions

- Use service-layer transaction boundaries when multiple persistence operations must succeed together.
- Keep read-only operations simple.
- Do not put business logic in controllers or mappers.

## Testing

v0.1 minimum:

- service tests for validation and status behavior
- API integration tests for Topic endpoints
- API integration tests for Experiment endpoints
- tests for duplicate slug, not found, invalid topic reference, validation errors
- H2-backed tests that do not need Docker

Preferred command:

```bash
cd backend
mvn test -Dspring.profiles.active=test
```

## Logging

- Log service startup.
- Log unexpected exceptions with enough context for diagnosis.
- Do not log secrets or full request bodies if they may contain sensitive data.

## Documentation

Backend changes should update:

- active spec when behavior changes
- runbook when commands/config change
- OpenAPI docs when API changes
- task or manifest evidence when verification completes

## Review Checklist

Before handing backend code to Claude Code review, confirm:

- [ ] Code follows Alibaba guideline intent for naming, exceptions, logs, collections, and SQL.
- [ ] Spring responsibilities are separated: controller validates and delegates; service owns business rules; mapper owns persistence.
- [ ] All external inputs are validated.
- [ ] All SQL is parameterized.
- [ ] Database schema changes are managed by Flyway migrations.
- [ ] API errors use the common envelope.
- [ ] H2 local tests pass.
- [ ] Runbook or verification evidence is updated.
