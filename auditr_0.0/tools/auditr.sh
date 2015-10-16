#!/bin/sh
# while IFS='' read -r line || [[ -n "$line" ]]; do
#	echo "Text read from file: $line"
# done < "$1"


cd CodeHawk/

if [ $1 = '-inf' ]; then
	rm *.jar
	cp $2 .

	./initialize.sh Project > /dev/null
	./chj_query.sh Project

	num=$(awk '/Loop invariants/{ print NR; exit }'  chresults/Project_all_loops.txt)
	num=$(($num-2))
	head -n $num  chresults/Project_all_loops.txt
elif [ $1 = '-diff' ]; then
	rm *.jar
	cp $2 .

	./initialize.sh Project > /dev/null
	./chj_query.sh Project

	cat chresults/Project_loop_method_different_depth_pairs.txt
elif [ $1 = '-hash' ]; then
	./chj_hashfinder $2
	cat _hashfinder_report.txt
	rm _hashfinder_report.txt
elif [ $1 = '-help' -o $1 = '-h' -o $1 = '--help' ]; then
	head -n 27 ../../README.txt | tail -n 10
else
	echo "Wrong input format!"
fi

# cd ..
