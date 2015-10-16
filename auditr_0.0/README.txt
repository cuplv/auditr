Contents:

-------------------------------------------------------------------------------------
README.txt  : this file
examples/   : a collection of examples demonstrating various vulnerabilities 
tools/      : a collection of various components of Auditr tool-chain to provide 
				various analyses for JAVA bytecode
-------------------------------------------------------------------------------------

Background:

The fundamental goal of the Auditr system is to allow analysts to  quickly and reliably 
pinpoint Java applications with exploitable security vulnerabilities.  Specifically, 
Auditr will focus on two kinds of security vulnerabilities related to space and 
time usage of programs:

1. Availability problems:, which arise when a user-provided input controls the 
   asymptotic complexity or termination of a program component. Since attackers can 
   substantially degrade system performance through carefully selected inputs, 
   availability problems can be exploited by malicious parties to launch denial of service 
   attacks.

2. Confidentiality problems:, which arise when two different user inputs result in 
   substantially different resource usage behaviors depending on the value of some 
   confidential data stored in the system.  If the program exhibits substantially different 
   space or time usage characteristics depending on user input, attackers can exploit 
   this behavior to learn confidential information through side channel attacks.

-------------------------------------------------------------------------------------

Status:

In the current version of Auditr system we present a set of tools built around highly 
scalable Codehawk JAVA analyzer that provide the following analyses as a first step towards 
pinpointing security vulnerabilities.  

1. [Infinite Loops] Detection of Potentially non-terminating Loops. Such vulnerability can 
   be exploited for DoS attacks (See Apache Tomcat Infinite Loop example).

2. [Different Complexity Loops] Detection of a call from within a loop that can be dispatched 
   to different methods having different complexity (nesting levels). Such vulernability 
   can be exploited in an availability attach (see JFreeChart framework example).

3. [Hash Collisions] Availability vulnerability dur to hash collision. The use of hash functions 
   for data storage and retrieval is a common cause for availability vulnerabilities as hash 
   collisions could significantly increase the cost to insert and delete data. The current 
   version simply checks for the keyword "hash" in the bytecode. In future versions this will 
   be made more robust by checking for a combination of primes and multiplication.	   

-------------------------------------------------------------------------------------

Usage:
1. cd to tools/
2. Execute the auditr tool with the following options: 
  a) ./auditr.sh -inf  /Absolute/Path/To/Foo.jar
     The ouput of this command is the following analysis: 
	 -- Total number of loops in the jar file along with number of loops that are proven to be
	    bounded by the Loopr tool
	 -- Total number of loops occurring at different depths
	 -- Maximum iteration and depth information for various loops, where 
	    -- max-it: the maxmium loop iteration with "?" indicating a potentially infinite loop.
	    -- depth: the depth of the loop
	    -- first pc: the byte code offset of the first instruction of the loop

  b) ./auditr.sh -diff /Absolute/Path/To/Foo.jar
     This command will analyze function calls inside a loop, and will show loop nesting level of 
     these functions. 

	For example, the output of analyzing Availability_example_1.jar (which is a 
	toy version of JFreeChart demonstrating the availability vulnerability) would be the 
	following. 
     
     ------------------------------------------------------------------------------
	73 JFreeChartToy.render(int)
	    1 JFreeChartToy$Circle.double drawItem()
	    1 JFreeChartToy$Shape.double drawItem()
   
	    2 JFreeChartToy$CandleStick.double drawItem()
     ------------------------------------------------------------------------------

	Where the "73" at the beginning of the message simply stands for the bytecode offeset of 
	the loop in method render(), while the "1" at the beginning of the second line stands for 
	a loop nesting level of 1 of the drawItem() method in Circle class, under the context of 
	being called inside a for-loop in the render() method. Similarly, the "2" at the start of 
	the last line means the drawItem() method in CandleStick class has a loop nesting level of 2, 
	under the same context. The only difference is that the drawItem() method in CandleStick 
	class has a loop inside, while its counterpart in Circle class has no loop inside.  
	       
  c) ./auditr.sh -hash /Absolute/Path/To/Foo.jar
	  This command will statically analyze the input jar files and report methods that potentially
	 call a hash function and report it.
	 
	For example, the output of analyzing jfreechart-1.0.19.jar (provided in the example directory) 
	is the following text. 
    ------------------------------------------------------------------------------
	Methods scanned: 9049
	CodeHawk Hash Function Report for  (10/15/2015 16:50:47)

	Methods scanned: 9049
	  hashCode     : 167
	    hash         : 0

		Other methods that may be hash functions: (0)
	   none found

	   Methods that call hash functions (1)
	  org.jfree.chart.HashUtilities.int hashCodeForPaint(java.awt.Paint)

    ------------------------------------------------------------------------------
	It reports all the methods that make calls to hash functions.

