# Cache-Simulator
This application simulates a cache by giving it a sample of read/write cache instructions, known as a trace file.

The following files should be in the same folder:

      -Simulator.java
   
      -Cache.java
   
      -Set.java
   
      -Block.java
   
      -Util.java
   
      -test1.txt (trace file used for input)
   
      -test2.txt (trace file used for input)
   

Simulator takes the following parameters:

      -cache size(bytes)
   
      -block size(bytes)
   
      -associativity (where 1 is direct mapped cache)
   
      -name of trace file
   

To run the simulator use the following command: (make sure files have been compiled)

      java Simulator <size of cache> <size of block> <associativity> <name of trace file>

The simulator reads from a trace file (generated by intel's pintool "pinatrace" which can
be downloaded from https://software.intel.com/en-us/articles/pintool-downloads) that 
has the following format: 0x37c852: W 0xbfd4b18c (where second column tells what the 
instruction will do, either read or write to cache, and third column is the address in the
cache where the read or write will take place).

The simulator outputs the following:

      -# of sets in cache
   
      -# of read instructions
   
      -# of write instructions
   
      -# of cache misses
   
      -# of cache hits

Things to be done:

      -implement write back policy
      
      -implement write through policy
      
      -add additional parameters to calculate cache performance
      
      -add 64 bit instruction functionality
