# Backend Standards

## Coding Baseline

Backend 使用项目定制基线：

```text
Alibaba Java Coding Guidelines
  + Spring Boot 3 engineering practice
  + MyBatis / SQL safety rules
  + Flyway migration discipline
  + automated formatting and static checks over time
```

Alibaba Java Coding Guidelines 是主要的人类可读基线，覆盖：

- naming
- constants
- collection usage
- exception handling
- logging
- unit testing discipline
- security awareness
- SQL conventions
- project structure judgment

Spring Boot practice 覆盖：

- controller / service / mapper responsibilities
- dependency injection
- configuration profiles
- validation and global exception handling
- actuator and OpenAPI integration
- test slices and integration tests

MyBatis persistence 必须遵守：

- 只使用 parameterized SQL。
- 不拼接 user input。
- mapper methods 必须和 XML statements 对齐。
- H2 和 PostgreSQL 的 SQL 差异必须记录。

v0.1 先通过 review 和 tests 执行这些规则。Static-analysis tooling 可以在 backend skeleton 稳定后再加入。

计划中的自动化顺序：

1. Maven test with H2 profile。
2. Formatter，例如 Spotless 或 Spring Java Format。
3. Checkstyle。
4. SpotBugs。
5. PMD / P3C rules。

不要为了完整 static-analysis setup 阻塞 v0.1 scaffolding。

## Stack

v0.1 backend target：

- JDK 17
- Spring Boot 3.x
- Maven
- MyBatis
- Druid
- Flyway
- H2 for local test
- PostgreSQL for later integration
- JUnit 5
- OpenAPI / Swagger

## Module Structure

使用 package-by-feature：

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

不要使用所有 domain 共用的 flat `controller/`、`service/`、`mapper/` package。

## Naming Rules

- Class names 使用 `UpperCamelCase`。
- Method 和 field names 使用 `lowerCamelCase`。
- Constants 使用 `UPPER_SNAKE_CASE`。
- DTO 名称要体现方向，例如 `CreateTopicRequest`、`TopicResponse`、`TopicQuery`。
- Business exception 应按失败概念命名，例如 `TopicNotFoundException`。
- Mapper XML namespace 必须匹配 mapper interface 的 fully qualified name。

## API Contract

使用 resource-oriented endpoints：

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

所有 endpoint 返回统一 envelope：

```json
{
  "success": true,
  "data": {},
  "errorCode": null,
  "errorMessage": null
}
```

Pagination data：

```json
{
  "records": [],
  "pageNo": 1,
  "pageSize": 10,
  "total": 0
}
```

## Validation

- 使用 Jakarta Bean Validation 校验 request bodies。
- 校验 path variables 和 query parameters。
- 在 service layer 校验 status values 和 status transitions。
- Topic slug format：lowercase letters、numbers、hyphens。
- Experiment 必须引用已存在 topic。

## Error Handling

使用 global exception handler。

必须支持的 error categories：

- validation failure
- topic not found
- experiment not found
- duplicate slug
- invalid topic reference
- database access failure
- unexpected system error

API errors 必须稳定、用户友好。不要暴露 SQL、stack traces、class names、connection strings 或 local file paths。

Logging / exception 规则：

- 不要 silently swallow exceptions。
- 不要随意 catch broad exceptions，除非是在边界转换异常。
- Unexpected exceptions 只在边界记录一次，并带足够上下文。
- 不记录 secrets、tokens、passwords 或完整 database URLs。

## Persistence

- SQL 放在 MyBatis mapper XML 或 mapper annotations。
- 只使用 parameterized queries。
- 不拼接 user input 到 SQL。
- Database schema 统一使用 Flyway migrations，目录为 `backend/src/main/resources/db/migration/`。
- 不使用临时 `schema.sql` 管理 application schema。
- H2 migrations 要尽量接近 PostgreSQL integration migrations。
- H2 / PostgreSQL 差异记录到 runbook。
- 避免 `SELECT *`，显式选择 columns。
- Pagination 必须有 deterministic ordering。
- 唯一业务键使用 unique constraints，例如 topic slug。
- 只在 query pattern 证明需要时增加 indexes，不添加 speculative indexes。

## Database Migration

- 所有 schema changes 使用 Flyway。
- Migration 命名使用 Flyway versioned migration format，例如 `V1__create_learning_core_tables.sql`。
- Migration shared 或 merged 后保持 append-only。
- 使用明确的 table、column、constraint、index names。
- 不要重写已应用 migration 来隐藏设计变化；新增 migration。
- Learning slice 中的重要 migration decision 要写进 runbook。

## Transactions

- 多个 persistence operations 必须一起成功时，在 service layer 使用 transaction boundary。
- Read-only operations 保持简单。
- 不把 business logic 放进 controllers 或 mappers。

## Testing

v0.1 minimum：

- service tests for validation and status behavior
- API integration tests for Topic endpoints
- API integration tests for Experiment endpoints
- tests for duplicate slug、not found、invalid topic reference、validation errors
- H2-backed tests，不需要 Docker

推荐命令：

```bash
cd backend
mvn test -Dspring.profiles.active=test
```

## Logging

- 记录 service startup。
- Unexpected exceptions 记录足够诊断上下文。
- 不记录 secrets，也不记录可能包含敏感数据的完整 request body。

## Documentation

Backend changes 应更新：

- behavior 变化时更新 active spec。
- commands/config 变化时更新 runbook。
- API 变化时更新 OpenAPI docs。
- verification 完成时更新 task 或 manifest evidence。

## Review Checklist

交给 Claude Code review 前，先确认：

- [ ] Code 符合 Alibaba guideline intent：naming、exceptions、logs、collections、SQL。
- [ ] Spring responsibilities 分离：controller validates and delegates；service owns business rules；mapper owns persistence。
- [ ] 所有 external inputs 已验证。
- [ ] 所有 SQL 使用 parameterized queries。
- [ ] Database schema changes 由 Flyway migrations 管理。
- [ ] API errors 使用 common envelope。
- [ ] H2 local tests 通过。
- [ ] Runbook 或 verification evidence 已更新。
