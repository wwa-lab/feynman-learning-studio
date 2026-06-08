# Prompts

Use this directory for durable prompts that are useful across runs or across agents.

Prompts should usually be lightweight pointers to repository files rather than copies of long specs.

Preferred shape:

```text
Read AGENTS.md, docs/00-project-rules/constitution.md, the active spec, the active task list, and the execution manifest.

Implement only the named task.
Run the verification command.
Report changed files, evidence, and risks.
```

Avoid storing one-off chat messages here unless they become reusable workflow assets.
