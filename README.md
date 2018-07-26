# frontiercargroup-qa

This is the solution for Task: automate test scenario for product search functionality 

Pre-requisite to run the tests : 
1. Java 1.8 should be installed on the machine.
2. Maven should be installed on the machine.
3. Firefox should be installed on the machine.

Steps to run : 
1. Clone the project using command : git clone https://github.com/hbanafal/frontiercargroup-qa.git
2. Change the directory to project's dir : cd frontiercargroup-qa
3. Run tests using command : mvn clean test
4. Open test reports in the folder : frontiercargroup-qa/test-output/emailable-report.html

Notes : 
1. This project for now is just created to run on Firefox browsers. 
2. Sometimes the API response is not same as the results on UI. May be for production Walmart 
is using different endpoint. So test may fail sometimes.
