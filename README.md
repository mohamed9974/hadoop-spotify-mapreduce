# Apache Hadoop’s MapReduce

## Table of Contents

- [About](#About)
- [Getting Started](#getting_started)
- [Usage](#usage)
- [Notes](#notes)
- [Contributing](../CONTRIBUTING.md)

## About <a name = "about"></a>

    []: # Apache Hadoop’s MapReduce

    Apache Hadoop’s MapReduce is a framework for distributed processing of large data sets.
    It is a Java implementation of the MapReduce algorithm.
    It is a distributed processing framework that allows you to write MapReduce programs
    in Java.
    you will use Apache Hadoop’s MapReduce to get insights from the Top Hits
    from Spotify 2000-2019 dataset.

    []: # Tasks
    • List the total duration (ms) of each song in the dataset (total)
    • List the average duration (ms) of each song (average)
    • List how many times an artist has been in the top hits list (popular)
    • Separate the songs according to whether they are explicit or not, then list the average popularity 
        for the songs in those two separate categories. Output explicit songs in part-r-00000 
        and non-explicit songs in part-r-00001 (explicitlypopular)
    • Partition the songs by year; first partition is the songs that came out on or before 2002
        (year ≤ 2002), the second partition is for songs that came out between 2002 and 2012
        (2002 < year ≤ 2012) and final partition is the songs that came out later than 2012
        (2012 < year). Report the average danceability of these 3 partitions (part-r-00000 to
        part-r-00002). (dancebyyear).

## Getting Started <a name = "getting_started"></a>

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See [deployment](#deployment) for notes on how to deploy the project on a live system.

    After cloning the project, you can run the following commands to get started:
    ./install.sh
### Prerequisites

What things you need to install the software and how to install them.
    1. Install Java 8
    2. Install Maven
```bash
    sudo apt-get install openjdk-8-jdk
    sudo apt-get install maven
```

### Installing

A step by step series of examples that tell you how to get a development env running.

Steps:

```
    $ cd /path/to/project 
```
```
    $ ./install.sh
```
 This is the expected Output at the end of the installation
```
[INFO] --- maven-jar-plugin:3.0.2:jar (default-jar) @ Hw3 ---
[INFO] 
[INFO] --- maven-install-plugin:2.4:install (default-install) @ Hw3 ---
[INFO] Installing /home/mohamed/cloud_computing/hadoop_server/app/java/./target/Hw3-1.0.jar to /home/mohamed/.m2/repository/org/example/Hw3/1.0/Hw3-1.0.jar
[INFO] Installing /home/mohamed/cloud_computing/hadoop_server/app/java/./pom.xml to /home/mohamed/.m2/repository/org/example/Hw3/1.0/Hw3-1.0.pom
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.092 s
[INFO] Finished at: 2022-06-18T18:37:08+03:00
[INFO] ------------------------------------------------------------------------
Jar file is installed in the main folder under the name Hw3.jar and the test cases are ran under output folder
```
## Usage <a name = "usage"></a>
how to use the system:
    1. cd /path/to/project
    2. ./runtest.sh
    3. mvn exec:java -Dexec.mainClass=Hw3 -Dexec.args="<test command> <input.csv> <outputfile>" -Dexec.cleanupDaemonThreads=false --quiet &> output/explicitlypopular.txt
    4. You will find the jar file in the target directory and a text file in the output directory.
    5. you can use the following command to run the program on hadoop: 
      hadoop jar target/hw3-1.0.jar Hw3 <test command> <input.csv> <outputfile>


## Notes <a name = "notes"></a>

    1. The input file is a csv file 
    2. The output file is a hadoop partitioned text file 
    3. The test command is one of the following:
        • total
        • average
        • popular
        • explicitlypopular
        • dancebyyear
    4. The output file will be in the output directory
    5. The code will be in the src directory
    6. The code requires the following dependencies:
        • Maven
        • Hadoop
        • apache commons
    7. Please make sure you have the correct version of the dependencies installed.
    8. The code will run in the main directory of the project.
    9. If you have any questions, please email me at mohamed.amin@metu.edu.tr