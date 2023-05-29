#!/bin/bash

echo 128 > /proc/sys/kernel/ns_last_pid; java -XX:CRaCCheckpointTo=crsb -XX:+UnlockDiagnosticVMOptions -XX:+CRTraceStartupTime -Djdk.crac.trace-startup-time=true -jar app.jar&
sleep 5
curl 'http://localhost:8080/driver' --header 'Content-Type: application/json' --data '{"name": "Driver1","email":"Driver@one.com"}'
curl 'http://localhost:8080/driver' --header 'Content-Type: application/json' --data '{"name": "Driver2","email":"Driver@two.com"}'
curl 'http://localhost:8080/driver' --header 'Content-Type: application/json' --data '{"name": "Driver3","email":"Driver@three.com"}'
sleep 10
echo "Creating Checkpoint now ..................."
jcmd app.jar JDK.checkpoint
sleep infinity