#!/bin/bash

# kill old running service
kill -9 $(ps ax | grep sbt | grep java | cut -d\  -f1)

nohup sbt -Djline.terminal=jline.UnsupportedTerminal run &

