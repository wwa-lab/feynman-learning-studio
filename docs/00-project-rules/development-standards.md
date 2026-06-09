# Development Standards

这是开发标准总入口。更详细的标准在：

- `docs/00-project-rules/design-standards.md`
- `docs/00-project-rules/backend-standards.md`
- `docs/00-project-rules/frontend-standards.md`

## Backend Standards

Backend code 使用项目定制基线：

```text
Alibaba Java Coding Guidelines
  + Spring Boot 3 engineering practice
  + MyBatis / SQL safety rules
  + Flyway migration discipline
```

第一条 implementation path 的目标 stack：

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

## Package Organization

按 feature/domain 组织代码：

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

不要把所有 controller、service、mapper 都放进一个 flat package。

## API Standards

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

所有 API 返回统一 envelope：

```json
{
  "success": true,
  "data": {},
  "errorCode": null,
  "errorMessage": null
}
```

Pagination data 应包含：

```json
{
  "records": [],
  "pageNo": 1,
  "pageSize": 10,
  "total": 0
}
```

## Validation

- 使用 Jakarta Bean Validation 校验 request body。
- 校验 path variables 和 query parameters。
- 在 service layer 校验 enum/status transitions。
- Topic slug 只允许 lowercase letters、numbers 和 hyphens。
- 可预期失败必须返回稳定 business error code。

## Persistence

- SQL 放在 MyBatis mapper XML 或 mapper annotations。
- 只使用 parameterized queries。
- 不拼接 user-controlled values 到 SQL。
- Database schema 统一通过 Flyway migrations 管理。
- H2 migration behavior 要尽量接近未来 integration database。
- H2/PostgreSQL 差异必须记录到 runbook。

## Error Handling

- 使用 global exception handler。
- validation、not-found、conflict、database、unexpected errors 都要转换成 API envelope。
- Server-side log 保留足够诊断上下文。
- API error message 不暴露 SQL、stack traces、connection strings 或 internal class names。

## Testing

v0.1 最低要求：

- service validation 和 status behavior 测试。
- Topic / Experiment API integration tests。
- H2-backed tests，不依赖 Docker 或外部数据库。
- 覆盖 validation errors、not-found、duplicate slug、invalid topic reference。

推荐命令：

```bash
cd backend
mvn test -Dspring.profiles.active=test
```

## Frontend Standards

Frontend code 使用项目定制基线：

```text
Vue official style guide
  + eslint-plugin-vue Vue 2 recommended rules
  + Airbnb JavaScript Style Guide where compatible
  + ElementUI workbench conventions
```

Frontend 开始后：

- 使用 Vue 2.6 + ElementUI。
- 构建 workbench screens，不做 marketing pages。
- UI 要适合工程师重复操作、扫描、对比。
- Form validation 要和 backend validation 对齐。
- 一致使用 API envelope。

## Documentation Standards

每个非平凡 implementation 至少应该更新以下一种：

- active spec
- runbook
- task checklist
- decision record
- article notes

Docs 必须说明：

- 改了什么。
- 怎么运行。
- 怎么验证。
- 还有什么不确定。
