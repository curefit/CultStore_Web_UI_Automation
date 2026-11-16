import json
import os
from pathlib import Path
import subprocess
import sys
import tempfile
import unittest

REPORTER=Path(__file__).resolve().parents[1]/'scripts/migration_summary.py'

class ReportContracts(unittest.TestCase):
    def verdict(self,status=None,execute=True):
        with tempfile.TemporaryDirectory() as tmp:
            root=Path(tmp)
            if status is not None:
                (root/'target/karate-reports').mkdir(parents=True)
                data=[{'elements':[{'type':'scenario','name':'case','steps':[{'result':{'status':status},'doc_string':{'value':'PRIVATE_PAYLOAD'}}]}]}]
                (root/'target/karate-reports/test.json').write_text(json.dumps(data))
            r=subprocess.run([sys.executable,str(REPORTER)],cwd=root,env=dict(os.environ,EXECUTE=str(execute).lower(),REQUIRE_KARATE='true'),capture_output=True)
            self.assertNotIn('PRIVATE_PAYLOAD',(root/'migration-results/results.json').read_text())
            return r.returncode
    def test_successful_scenario_passes(self):self.assertEqual(0,self.verdict('passed'))
    def test_failed_or_unresolved_scenarios_fail(self):
        for s in ['failed','pending','undefined','ambiguous']:self.assertNotEqual(0,self.verdict(s))
    def test_empty_and_all_skipped_live_runs_fail(self):
        self.assertNotEqual(0,self.verdict())
        self.assertNotEqual(0,self.verdict('skipped'))
    def test_compile_only_is_not_live_evidence(self):self.assertEqual(0,self.verdict(execute=False))

if __name__=='__main__':unittest.main()
