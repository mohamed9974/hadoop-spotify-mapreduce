#!/usr/bin/sh
# We will run this file to install the maven project using the maven command and the maven archetype command
# this mvn project is going to use the pom.xml file to install the project

# check if the folder target exists, delete it if it does
if [ -d "target" ]; then
    rm -rf target  Hw3.jar
fi

echo "please install maven the next command will install it 'sudo apt install maven' enter your password if you have one"
sudo apt-get install maven
sleep 1
mvn clean -f "./pom.xml"
# wait for the maven project to finish
sleep 1
mvn validate -f "./pom.xml"
sleep 1
mvn compile -f "./pom.xml"
sleep 1
mvn package -f "./pom.xml"
sleep 1
mvn verify -f "./pom.xml"
sleep 1
mvn install -f "./pom.xml"
sleep 1
chmod 777 ./target/Hw3-1.0.jar
sleep 1
cp -r ./target/Hw3-1.0.jar ./Hw3.jar
echo "Jar file is installed in the main folder under the name Hw3.jar and to run the test cases you need to run the runtest.sh file"