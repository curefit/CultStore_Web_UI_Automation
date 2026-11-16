# Jenkins v2 jobs migrated to manual Actions

The default EXECUTE=false compiles source only. EXECUTE=true executes the selected original test target. Compile-only results are explicitly labelled and do not establish live browser/API health. Original source and assertions are unchanged.

Both entry points require CONFIRM_EFFECTS=true for execution. Cultstore_Web_Automation had no Jenkins build steps, so its configured HeadlessBaseTest target is restored explicitly; this is a recovered executable target, not a claim that an empty Jenkins job ran tests. The other entry preserves the POM TestNG suite via mvn test. Chrome is provided by the hosted x64 runner; no amd64-only Chrome package is installed on the ARM cluster.

Raw logs and HTTP data are withheld from artifacts. Sanitized HTML/JSON reports preserve names and statuses; missing results or failed scenarios fail acceptance independently of the Maven wrapper. Live credentials, any alert destination, and site/backend behavior require validation before Jenkins cutover.

| Jenkins job | Workflow |
|---|---|
| Cultstore_Web_Automation | `cultstore-web-automation-tests.yaml` |
| Cultstore Web UI Automation | `cultstore-web-ui-tests.yaml` |

Local validation: repository source compiled with JDK 17; all workflow definitions passed actionlint. No live browser journey, order, or payment was executed.

Configure the existing alert destination as the repository secret `ALLQUIET_WEBHOOK_URL` before cutover. Executed jobs preserve the All Quiet firing/resolved signal with an Actions-specific identity; compile-only PR checks never send notifications. Missing destination is explicitly reported rather than claimed delivered.
