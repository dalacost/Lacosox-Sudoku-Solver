#!/bin/bash
echo borrando archivos anteriores...
rm -rf clases/*
javac -sourcepath src/ -d clases/ src/*.java -verbose -Xstdout /dev/stdout #| grep -e total -e wrote
