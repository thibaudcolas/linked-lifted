#!/usr/bin/env bash
#
# Callfinder helps you find if / where functions / classes / variables are used.
# It uses grep to find occurences of given strings.
# @author tcolas
#
# Usage : ./callfinder.sh FILE THRESHOLD
# FILE is the path to a file where each line is a function to search for.
# Ex : grep my/js/file.js 'function' > function-file.txt
# Threshold allows you to select what to display.

if [[ $# == 1 ]]; then
	FILE=$1
else
    # Default list file.
	FILE=callfinder.txt
fi

if [[ $# == 2 ]]; then
	THRESHOLD=$2
else
    # Default threshold : only show unused functions.
	THRESHOLD=1
fi

while read line; do 
    count=`grep -n --color -r $line * --exclude $FILE | wc -l`
    echo "-------------------------------------------------------------------------------" 
    echo "|" $count " > " $line
    if (($count <= $THRESHOLD)); then
        echo "|"
    	grep -n --color -r $line * --exclude $FILE
    fi
done < $FILE