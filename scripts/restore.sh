#!/bin/bash

java -XX:CRaCRestoreFrom=crsb&
PID=$!
trap "kill $PID" SIGINT SIGTERM
wait $PID
