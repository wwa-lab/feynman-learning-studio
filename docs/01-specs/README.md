# Specs

Specs 是非平凡实现工作的 source of truth。

新增 spec 时，从 `template.md` 开始。

每份 spec 至少要说明：

- Goal：这次要解决什么。
- Scope：范围内做什么。
- Non-goals：明确不做什么。
- Acceptance criteria：怎么判断完成。
- Verification evidence：用什么 test/run/smoke 证明。
- Open questions：还没决定的问题是什么。

不要让 AI 生成的代码反过来偷偷改变 spec。
如果实现中发现 spec 不够清楚，先补 spec 或记录 decision。
