# Hadoop example to solve single word anagrams
Simple hadoop example to solve single word anagrams

# Build

    git clone https://github.com/parekhparth/Hadoop-AnagramSolver.git

    cd Hadoop-AnagramSolver

    mvn clean install
  
(above command builds the 1.0-SNAPSHOT jar)

# prerequisites
* you should have hadoop installed (Follow <a href='http://ragrawal.wordpress.com/2012/04/28/installing-hadoop-on-mac-osx-lion/'>this</a> guide if you're developing on Mac)
* you should have latest Java (1.5+) installed
* you should have Maven installed

# Execute
after you build the jar, you can find the anagrams using following command:

Usage:

    hadoop -jar <Hadoop-AnagramSolver.jar> com.parthparekh.hadoop.AnagramSolverJob <numberOfMaps> <wordlistFilePath> <outputFilePath> <anagramWord>
      numberOfMaps - number of map jobs you want to run
      wordlistFilePath - Hadoop FS path for the wordlist file (you%27 have to upload the wordlist file to Hadoop FS before this)
      outputFilePath - Hadoop FS path for output directory
      anagramWord - word for which you want to find anagrams

## useful commands

Create directory in Hadoop FS

    hadoop fs -mkdir Hadoop-AnagramSolver

Delete directory from Hadoop FS

    hadoop fs -rmr Hadoop-AnagramSolver/output

List all the files from Hadoop FS

    hadoop fs -ls Hadoop-AnagramSolver/

Print contents of file from Hadoop FS

    hadoop fs -cat Hadoop-AnagramSolver/testwordlist

Put files into Hadoop FS

    hadoop fs -put testwordlist Hadoop-AnagramSolver/

## Example

upload the wordlist <a href='https://github.com/parekhparth/Hadoop-AnagramSolver/blob/master/wordlist/wordlist.txt'>file</a> to Hadoop FS using following command:
    hadoop fs -mkdir Hadoop-AnagramSolver
    hadoop fs -copyFromLocal wordlist.txt Hadoop-AnagramSolver/

Below command will execute Hadoop MapReduce AnagramSolverJob to find anagrams for 'listen'

    hadoop jar target/Hadoop-AnagramSolver-1.0-SNAPSHOT.jar com.parthparekh.hadoop.AnagramSolverJob 4 Hadoop-AnagramSolver/wordlist.txt Hadoop-AnagramSolver/output listen

    13/04/21 14:56:50 WARN mapred.JobClient: Use GenericOptionsParser for parsing the arguments. Applications should implement Tool for the same.
    13/04/21 14:56:50 WARN util.NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
    13/04/21 14:56:50 WARN snappy.LoadSnappy: Snappy native library not loaded
    13/04/21 14:56:50 INFO mapred.FileInputFormat: Total input paths to process : 1
    13/04/21 14:56:50 INFO mapred.JobClient: Running job: job_201304210050_0023
    13/04/21 14:56:51 INFO mapred.JobClient:  map 0% reduce 0%
    13/04/21 14:56:58 INFO mapred.JobClient:  map 50% reduce 0%
    13/04/21 14:57:03 INFO mapred.JobClient:  map 100% reduce 0%
    13/04/21 14:57:08 INFO mapred.JobClient:  map 100% reduce 33%
    13/04/21 14:57:09 INFO mapred.JobClient:  map 100% reduce 100%
    13/04/21 14:57:10 INFO mapred.JobClient: Job complete: job_201304210050_0023
    13/04/21 14:57:10 INFO mapred.JobClient: Counters: 27
    13/04/21 14:57:10 INFO mapred.JobClient:   Job Counters 
    13/04/21 14:57:10 INFO mapred.JobClient:     Launched reduce tasks=1
    13/04/21 14:57:10 INFO mapred.JobClient:     SLOTS_MILLIS_MAPS=18853
    13/04/21 14:57:10 INFO mapred.JobClient:     Total time spent by all reduces waiting after reserving slots (ms)=0
    13/04/21 14:57:10 INFO mapred.JobClient:     Total time spent by all maps waiting after reserving slots (ms)=0
    13/04/21 14:57:10 INFO mapred.JobClient:     Launched map tasks=4
    13/04/21 14:57:10 INFO mapred.JobClient:     Data-local map tasks=4
    13/04/21 14:57:10 INFO mapred.JobClient:     SLOTS_MILLIS_REDUCES=10751
    13/04/21 14:57:10 INFO mapred.JobClient:   File Input Format Counters 
    13/04/21 14:57:10 INFO mapred.JobClient:     Bytes Read=1047621
    13/04/21 14:57:10 INFO mapred.JobClient:   File Output Format Counters 
    13/04/21 14:57:10 INFO mapred.JobClient:     Bytes Written=43
    13/04/21 14:57:10 INFO mapred.JobClient:   FileSystemCounters
    13/04/21 14:57:10 INFO mapred.JobClient:     FILE_BYTES_READ=86
    13/04/21 14:57:10 INFO mapred.JobClient:     HDFS_BYTES_READ=1048105
    13/04/21 14:57:10 INFO mapred.JobClient:     FILE_BYTES_WRITTEN=272572
    13/04/21 14:57:10 INFO mapred.JobClient:     HDFS_BYTES_WRITTEN=43
    13/04/21 14:57:10 INFO mapred.JobClient:   Map-Reduce Framework
    13/04/21 14:57:10 INFO mapred.JobClient:     Map output materialized bytes=104
    13/04/21 14:57:10 INFO mapred.JobClient:     Map input records=109583
    13/04/21 14:57:10 INFO mapred.JobClient:     Reduce shuffle bytes=104
    13/04/21 14:57:10 INFO mapred.JobClient:     Spilled Records=10
    13/04/21 14:57:10 INFO mapred.JobClient:     Map output bytes=70
    13/04/21 14:57:10 INFO mapred.JobClient:     Total committed heap usage (bytes)=823476224
    13/04/21 14:57:10 INFO mapred.JobClient:     Map input bytes=1044753
    13/04/21 14:57:10 INFO mapred.JobClient:     Combine input records=0
    13/04/21 14:57:10 INFO mapred.JobClient:     SPLIT_RAW_BYTES=484
    13/04/21 14:57:10 INFO mapred.JobClient:     Reduce input records=5
    13/04/21 14:57:10 INFO mapred.JobClient:     Reduce input groups=1
    13/04/21 14:57:10 INFO mapred.JobClient:     Combine output records=0
    13/04/21 14:57:10 INFO mapred.JobClient:     Reduce output records=1
    13/04/21 14:57:10 INFO mapred.JobClient:     Map output records=5
    
    Single word anagrams for 'listen' are: 
    eilnst
    silent
    tinsel
    enlist
    inlets
    listen	

# LICENSE

This project is under "Do whatever you want" MIT License => http://www.tldrlegal.com/license/mit-license
