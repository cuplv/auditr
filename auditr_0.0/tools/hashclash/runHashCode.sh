rm sol.dimacs
rm sol.hex
BASE=0
PRIME=31
NCHAR=5         # number of characters
./hashclash $1 -b $BASE -p $PRIME -n $NCHAR > encoded.cnf
cat encoded.cnf | grep "characters"
./allsat/core/allsat encoded.cnf -slimit=1000 | grep " 0" > sol.dimacs
cat sol.dimacs | ./decode $NCHAR > sol.hex
java PrintHashes
