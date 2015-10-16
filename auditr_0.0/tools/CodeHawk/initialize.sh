#!/bin/sh

# --------------------------------------------------------------------------------
# Removes all previous analysis results (if present), creates the analysis directories
# and runs the initialization.
# If -save_summaries is set in chj_initialize.sh it will also create class summaries
# in chanalysis/chsummaries.
# --------------------------------------------------------------------------------

rm -rf chanalysis
rm -rf chresults

mkdir chanalysis
mkdir chresults
cd chanalysis
mkdir chdata
mkdir chsummaries
cd ..
./chj_initialize.sh $1
cd chanalysis/chsummaries
echo "Create jar of class summaries ... "
jar cf $1_app.jar *
