
Technology:
Java programming language. (Verison 1.8)
Selenium Webdriver libraries
Rest Assured libraries
TestNG library
Implemented as Maven project.

Project directory has multiple src folders in it.
src/main/java - has java based utilities
src/main/webdriver - has webdriver libraries related utilities
src/main/connectors - this src works as a bridge between all utility srs and the project specific test src

src/test/java - dedicated this src directory to prepare test classes alone.
src/test/por - dedicated this src directory to prepare all the UI pages page object classes.
src/test/testutils - Helps to store re usable functionalities of the application

The 3 phases tests are written in the test class - WeatherTest.java

How to Import:
Open any IDE (eclipse/ Netbeans)
Import the project folder ie "TestVagrantWeatherReportTest" as a maven project.
Note- Make sure to jave all relevant above ilsted technology plugins to the IDE.

How to Run:
Way 1:
Right click on the file "WeatherTest.java" and run as TestNG.
Way 2:
A project folder has a directory "Xml_Executors", and you can right click on any of the xml file and can perform run as TestNg Suite again.
Way 3:
A project folder has a file "pom.xml", right click on the file and Run As - maven install / maven test.
(Even can redirect to the directory using command prompt and execute the same command)


Jenkins:
Jenkins job can be setup considering maven plug in and by specifying the pom.xml directory path as a executable file in the job config.
