"""Publish only test names/statuses; never copy HTTP logs, fixtures or credentials."""
import html
import json
import os
from pathlib import Path
import sys
import xml.etree.ElementTree as ET

cases=[]
for p in Path('target').rglob('*'):
    if not p.is_file(): continue
    if p.suffix=='.json':
        try: data=json.loads(p.read_text())
        except (ValueError, UnicodeError): continue
        if not isinstance(data,list): continue
        for f in data:
            if not isinstance(f,dict):continue
            for s in f.get('elements',[]):
                if s.get('type')!='scenario':continue
                states=[x.get('result',{}).get('status') for x in s.get('steps',[])]
                status='failed' if any(x not in ['passed','skipped'] for x in states) else ('skipped' if not states or all(x=='skipped' for x in states) else 'passed')
                cases.append(dict(name=s.get('name',''),status=status,format='cucumber'))
    elif p.name.startswith('TEST-') and p.suffix=='.xml':
        for s in ET.parse(p).getroot().iter('testcase'):
            status='failed' if s.find('failure') is not None or s.find('error') is not None else ('skipped' if s.find('skipped') is not None else 'passed')
            cases.append(dict(name=s.get('classname','')+'.'+s.get('name',''),status=status,format='junit'))
executed=os.environ.get('EXECUTE')=='true'
Path('migration-results').mkdir(exist_ok=True)
Path('migration-results/results.json').write_text(json.dumps({'executed':executed,'cases':cases},indent=2))
rows=''.join('<tr><td>'+html.escape(x['name'])+'</td><td>'+x['status']+'</td></tr>' for x in cases)
Path('migration-results/results.html').write_text('<!doctype html><meta charset="utf-8"><title>Migration test outcomes</title><h1>'+('Executed tests' if executed else 'Compile only — no live test evidence')+'</h1><table>'+rows+'</table>')
if executed:
    if not cases or all(x['status']=='skipped' for x in cases) or any(x['status']=='failed' for x in cases):sys.exit('Missing or failed test results')
    if os.environ.get('REQUIRE_KARATE')=='true' and not any(x['format']=='cucumber' and x['status']=='passed' for x in cases):sys.exit('No Karate scenarios; wrapper success is insufficient')
