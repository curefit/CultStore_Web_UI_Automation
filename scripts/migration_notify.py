"""Preserve the Jenkins All Quiet job outcome signal without publishing its secret URL."""
import os
import sys
import urllib.parse
import urllib.request

base=os.environ.get('ALLQUIET_WEBHOOK_URL','')
status=os.environ.get('RUN_CONCLUSION','')
if not base:
    print('All Quiet destination is not configured; notification cutover remains pending.')
    sys.exit(0)
if status not in ['success','failure']:
    sys.exit(0)
key=os.environ['GITHUB_WORKFLOW']+':'+os.environ.get('TEST_ENV','default')
run_url=os.environ['GITHUB_SERVER_URL']+'/'+os.environ['GITHUB_REPOSITORY']+'/actions/runs/'+os.environ['GITHUB_RUN_ID']
query=urllib.parse.urlencode({'alert_id':'github-actions:'+key,'status':'resolved' if status=='success' else 'firing','level':'high','alert_title':'GitHub job '+status+': '+os.environ['GITHUB_WORKFLOW'],'alert_desc':run_url})
request=urllib.request.Request(base+('&' if '?' in base else '?')+query,data=b'',method='POST')
try:
    with urllib.request.urlopen(request,timeout=15) as response:
        if not 200 <= response.status < 300:raise OSError('Notification rejected')
except OSError:
    sys.exit('All Quiet delivery failed; endpoint details withheld.')
