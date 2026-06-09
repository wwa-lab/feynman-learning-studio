# Harness Loop

## 目的

本项目的 Harness Loop 不是为了让 AI 自动飞奔，而是为了让每个学习 slice 都可解释、可验证、可反馈、可沉淀。

默认工作方式：

```text
learning goal
  -> skill selection
  -> skill-native output
  -> project supplement
  -> implementation loop
  -> understand-anything code map
  -> harness gates
  -> runbook evidence
  -> Feynman reflection
  -> rule / decision / next slice
```

## 三层 Loop

### Slice Loop

Slice Loop 用于一次完整学习切片，例如 `v0.1-backend-learning-core`。

```text
goal
  -> spec
  -> design
  -> tasks
  -> implementation
  -> verification
  -> code understanding
  -> runbook
  -> reflection
  -> next slice
```

每个非平凡 slice 至少留下：

- spec：这次要解决什么、不解决什么、如何验收。
- skill-native artifacts：保留 skill 原始输出。
- project supplements：补足本 repo 的 SDD、教学、验证要求。
- manifest：固定输入、约束、分支、验证命令、风险。
- code map：用 `understand-anything` 帮助学习者理解 AI-generated code 的结构和关系。
- runbook：如何测试、如何运行、如何手动验证、有哪些踩坑。
- Feynman reflection prompts：学习者能不能用自己的话讲清楚。

### Code Understanding Loop

Code Understanding Loop 用于把 AI 生成的 Java / Spring Boot 代码变成可学习、可讲解的地图。它特别面向有 AS/400 / IBM iSeries 背景、但 Java 项目经验较少的学习者。

推荐流程：

```text
implementation verified
  -> run /understand --language zh
  -> inspect dashboard / knowledge graph
  -> identify reading path
  -> explain with AS/400 analogy
  -> record questions and conclusions
  -> update runbook or reading guide
```

关注三类问题：

- 入口在哪里：Spring Boot application、controller、test 从哪里开始。
- 请求怎么走：API request 如何经过 controller / service / mapper / SQL。
- 配置怎么连：`application.yml`、Flyway migration、MyBatis mapper、Swagger/OpenAPI 分别负责什么。

`.understand-anything/` 默认是可再生成的本地索引，不自动作为长期 source of truth。长期沉淀应写入：

- 当前 slice runbook。
- `docs/02-design/` 下的中文 reading guide。
- `docs/05-decisions/` 下的长期设计选择。
- `docs/06-articles/` 下的 Feynman explanation。

### Step Loop

Step Loop 用于一次小实现或小文档变更。

```text
pick one task
  -> state expected evidence
  -> edit
  -> run narrow check
  -> inspect result
  -> record issue/fix when needed
  -> continue, adjust, or stop
```

原则：

- 一次只推动一个小目标。
- 每次 edit 后都尽量运行最窄但有意义的 check。
- 失败不是噪音，失败是 harness 给出的学习信号。
- 如果问题有学习价值，记录到当前 slice runbook。

IBM iSeries 类比：

- Step Loop 像一次小 job 验证。
- test output / server log / Swagger result 像 joblog。
- runbook 是可复用的操作手册和排障记录。

### Feedback Loop

Feedback Loop 用于把用户偏好变成项目规则。

用户反馈默认按这四类吸收：

```text
Keep：这个风格保留
Change：这个以后调整
Avoid：这种方式不要再用
Rule：固化成项目规则
```

反馈落点：

- 代码结构、文档语言、工作流偏好 -> `docs/00-project-rules/`
- 当前 slice 的踩坑和 fix -> `docs/04-runbooks/`
- 长期设计选择 -> `docs/05-decisions/`
- 可发布的学习解释 -> `docs/06-articles/`

## Skill 使用规则

Skill 是 harness 的入口，不是最终验收标准。

流程：

```text
choose skill
  -> read skill instructions
  -> produce skill-native output
  -> preserve it as its own artifact
  -> compare against harness gates
  -> add project supplement for missing evidence
```

不要把 skill 原始输出和项目补充混在一起。补充内容应使用 `{artifact}-supplement.md` 或 `Project Supplement` 标明。

## Evidence 规则

每轮 loop 结束时，应尽量留下具体 evidence：

- 读了哪些 source of truth。
- 修改了哪些文件。
- 跑了什么命令。
- 看到什么结果。
- 哪些 gate 通过。
- 哪些 gate 跳过，以及为什么。
- 哪些问题被记录到 runbook。

没有 evidence 的“完成”不能算完成，只能算候选完成。

## Stop 条件

一个 slice 可以停止在三种状态：

### Done

- Scope 内需求完成。
- Harness gates 通过或有明确跳过理由。
- Runbook 和学习问题记录已更新。
- 下一步清楚。

### Blocked

- 同一个 blocking condition 反复出现。
- 继续推进会靠猜测破坏 source of truth。
- 需要用户做产品、范围、环境或凭证决策。

### Deferred

- 当前发现的问题真实存在，但不属于本 slice。
- 已记录 follow-up，不偷偷塞进当前实现。

## 输出口径

每轮交付时，优先说明：

- 完成了什么。
- 用什么 evidence 证明。
- 没做什么，以及为什么。
- 学到了什么或记录了什么问题。
- 下一步应该进入哪个 loop。
