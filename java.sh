#!/usr/bin/env bash

declare -i count
#统计java代码行数
a=`find ./src -name "*.java"|xargs cat|wc -l`
b=`find ./src -name "*.xml"|xargs cat|wc -l`
c=`find ./src -name "*.sql"|xargs cat|wc -l`
count=${a}+${b}+${c}
echo "total java: ${count} line."