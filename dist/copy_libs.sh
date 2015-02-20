#!/bin/sh

SRC=$1/../libs/
DST=$1/../processing-library/mouseandkeylogger/library

if [ -d "$DST" ]; then
	rm -rf "$DST"
fi
mkdir "$DST"

cp -R "$SRC" "$DST"

SRC=$1/../../netbeans/dist/mouseandkeylogger.jar
DST=$1/../processing-library/mouseandkeylogger/library

cp "$SRC" "$DST"