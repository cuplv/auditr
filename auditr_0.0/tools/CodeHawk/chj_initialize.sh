#!/bin/sh

CHJ=$PWD
SUMMARIES=$CHJ/jdksummaries/jdk.jar
JCHINITIALIZE=$CHJ/chj_initialize

# List of application jars included in the project
JARS='lib/*.jar *.jar'

# The analysis initializer will create the following files in chanalysis/chdata: 
# - <project>_dictionary.xml :  
#      contains index numbers for (fully qualified) class names, method signatures,
#      and class method signatures
# - <project>_signatures.xml :
#      a mapping from method signatures to classes that provide an implementation
#      for that method signature
# - <project>_missing_classes.xml :
#      list of classes that are referenced in the byte code, but that were not 
#      found in the application jars or summaries (this includes jdk classes
#      not yet summarized, since we run without the rt.jar on the classpath) .
# - <project>_callgraph.xml :
#      list of callgraph edges, where each edge has the following attributes:
#      - ix: index number of the class method signature of the call site
#      - pc: bytecode offset of the call site
#      - tag: (cv = constrained-virtual), (v = virtual), (r = resolved)
#             (cn-empty = virtual call, no targets found)
#             (if-empty = interface call, no targets found)
#      - cn-ix (only if the method is resolved): the index number of the 
#            target class
#      - tt (only if the method is resolved):
#            p = private; s = static ; fc = final class ; fm = final method ; 
#            la = local analysis
#      and, in case of virtual or constrained-virtual targets), a child-element
#            s-tgts, with child-elements ix-list that contains the indices of
#            the classes that may be targets, and a child-element d-tgts, with
#            child-elements ix-list that contains the indices of classes that
#            were observed to be the target in an actual run.
#      Example:
#         <edge ix="12510" pc="269" tag="v">
#           <s-tgts ixs="79,2007" size="2"/>
#           <d-tgts ixs="79"/><!-- to be added by dynamic analysis -->
#         </edge>
#
#      list of callback edges with the following attributes:
#      - ix: index number of the class method signature that may be subject to
#            callback
#      - name: name of method signature (redundant, included for now for
#            convenience of the human reader, will be removed later)
#      and a child-element d-srcs (to be provided by dynamic analysis) with 
#      child-elements <src> with attributes
#            - cn: fully qualified class name 
#            - cn-ix (optional): class name index, if available in the dictionary
#            - ms: method signature
#            - ms-ix (optional): method signature index, if available in the
#              dictionary)
#

$JCHINITIALIZE -project $1 -summaries $SUMMARIES -jars $JARS
