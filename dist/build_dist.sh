#!/bin/sh

ROOT=$(pwd)
VERSION=$1

printJob()
{
	echo ""
	echo "################################"
	echo "# "$1
	echo "################################"
}

printJob "cleaning dir"
sh $ROOT/clean.sh $ROOT
printJob "copying libs"
sh $ROOT/copy_libs.sh $ROOT
printJob "copying src"
sh $ROOT/copy_src.sh $ROOT
printJob "creating processing sketches"
sh $ROOT/create-processing-sketches.sh $ROOT

printJob "packing zip"
sh $ROOT/pack-zip.sh $ROOT $VERSION
printJob "done"
