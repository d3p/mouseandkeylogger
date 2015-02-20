#!/bin/sh

DST=$1/../processing-library/mouseandkeylogger

if [ -d "$DST" ]; then
	rm -rf "$DST"
fi
mkdir "$DST"
