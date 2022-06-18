#!/usr/bin/sh
# check if maven is installed on the system or not if not install it send a message to the user to install it
if [ -z "$(which mvn)" ]; then
    echo "please run the install.sh file to install maven"
    exit 1
fi
# check if the folder target exists, delete it if it does
if [ -d "target" ]; then
    echo "please run the install.sh file to install the application"
    exit 1
fi
if [ -d "output" ]; then
    rm -rf output* 
fi
# we will run the jar file using the java command with the main class is the main class in the jar file of the project
echo "starting the first test case \'total\'..."
mvn exec:java -Dexec.mainClass=Hw3 -Dexec.args="total songs_normalize.csv output/total" -Dexec.cleanupDaemonThreads=false --quiet &> output/total.txt 
echo "starting the second test case \'Average\'..."
mvn exec:java -Dexec.mainClass=Hw3 -Dexec.args="average songs_normalize.csv output/average" -Dexec.cleanupDaemonThreads=false --quiet &> output/average.txt
echo "starting the third test case \'Popular\'..."
mvn exec:java -Dexec.mainClass=Hw3 -Dexec.args="popular songs_normalize.csv output/popular" -Dexec.cleanupDaemonThreads=false --quiet &> output/popular.txt
echo "starting the forth test case \'Explicitly Popular\'..."
mvn exec:java -Dexec.mainClass=Hw3 -Dexec.args="explicitlypopular songs_normalize.csv output/explicitlypopular" -Dexec.cleanupDaemonThreads=false --quiet &> output/explicitlypopular.txt
echo "starting the fifth test case \'Dance by year\'..."
mvn exec:java -Dexec.mainClass=Hw3 -Dexec.args="dancebyyear songs_normalize.csv output/dancebyyear" -Dexec.cleanupDaemonThreads=false --quiet &> output/dancebyyear.txt
sleep 1
echo "the test cases are ran under output folder"