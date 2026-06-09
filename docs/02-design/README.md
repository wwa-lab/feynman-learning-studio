# Design

这个目录保存从已接受 spec 推导出来的 architecture notes、module boundaries、data model decisions 和 technical designs。

当一个 slice 有 3 个以上组件、状态流转、数据关系或 handoff 时，先画 visual model。

默认 diagram 格式：

- 可编辑源文件：`{feature-id}.drawio`
- 有条件时导出预览：`{feature-id}.drawio.png` 或 `{feature-id}.drawio.svg`

推荐 diagram 类型：

- architecture diagram
- sequence diagram
- ER diagram
- state transition diagram
- learning-loop diagram

Draw.io source files 是设计本身的一部分，不是一次性草稿。

v0.1 中文阅读导览：

```text
docs/02-design/v0.1-backend-learning-core-reading-guide.md
```
