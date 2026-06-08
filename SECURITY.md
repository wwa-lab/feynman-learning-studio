# Security Policy

## Supported Versions

This project is in early development. Security fixes apply to the default branch and active development branches.

## Reporting A Vulnerability

Please do not open public issues for secrets, exploitable vulnerabilities, or sensitive system details.

Use a private channel with the repository owner until a disclosure process is formalized.

Include:

- Affected area.
- Reproduction steps.
- Impact.
- Suggested remediation, if known.

## Project Security Baseline

- No secrets in source code.
- Validate all external input.
- Use parameterized SQL.
- Do not expose stack traces or database details in API responses.
- Review API, persistence, auth, and external integration changes before release.
