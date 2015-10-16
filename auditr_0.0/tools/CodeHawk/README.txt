
Contents:
-------------------------------------------------------------------------------
chj_initialize   : codehawk executable to analyze jar files
chj_query        : codehawk executable to query results produced by chj_query

chj_initialize.sh : shell script to invoke chj_initialize
chj_query.sh      : shell script to invoke chj_query

initialize.sh    : shell script that prepares the directory for analysis
                     and invokes chj_initialize

jdk.jar          : JDK library method summaries, used by chj_initialize
--------------------------------------------------------------------------------


Instructions:

- Edit chj_initialize.sh with the locations of the jars to be analyzed
    and the location of the chj_initialize executable and jdk.jar
- run ./initialize.sh <project-name>
- run ./chj_query.sh <project-name>
      results are saved in the directory chresults
