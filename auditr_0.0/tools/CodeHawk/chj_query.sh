#!/bin/sh

CHJ=$PWD

SUMMARIES=$CHJ/jdksummaries/jdk.jar
JCHQUERY=$CHJ/chj_query

# --------------------------------------------------------------------------------
# Query facility to obtain information about loops.
# 
# Queries currently supported:
#   no_calls           : list all loops that have no calls included
#   loopdepth_histogram: show the number of loops for different depths
#   loop_method_pairs  : list loops from within methods are called with loops
#
# The output of all of these queries can be restricted by specifying 
#  -mindepth x
# where x is the minimum depth a loop (or combination of loops) needs to have to
# be shown
#
# e.g. 
# 
#   JCHQUERY -project $1 -mindepth 1 -queries all_loops
#
# would only show the combination of methods that together have a depth of 1 or more.
#
# The results are saved in the directory chresults
# ---------------------------------------------------------------------------------

#$JCHQUERY -project $1 -queries all_loops loop_method_different_depth_pairs
$JCHQUERY -project $1 -queries all_loops loop_method_different_depth_pairs loop_method_pairs
#$JCHQUERY -project $1 -queries all_loops loopdepth_histogram
