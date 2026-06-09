# Prompts

这个目录保存跨多次运行、跨工具可复用的 prompts。

Prompt 应该尽量是轻量指针，指向 repo 里的 source of truth，而不是复制一大段 spec。

推荐形态：

```text
Read AGENTS.md, docs/00-project-rules/constitution.md, the active spec, the active task list, and the execution manifest.

Implement only the named task.
Run the verification command.
Report changed files, evidence, and risks.
```

不要把一次性的聊天消息都存进来。
只有当某个 prompt 真的变成可复用 workflow asset 时，才放到这里。
