#!/bin/sh

LIBRARY=mouseandkeylogger
VERSION=$(printf %03d%s ${2%.*})

echo "* packing "$LIBRARY-$VERSION.zip

find $1/.. -name ".DS_Store" -print0 | xargs -0 rm -f
cd $1/../processing-library/
zip --quiet -r $1/../dist/$LIBRARY-$VERSION.zip ./$LIBRARY
cd $1/../dist/