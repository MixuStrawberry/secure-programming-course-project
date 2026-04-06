# Java Dependency and Vulnerability scanner <br>(Maven and Gradle projects)

This project is done as a part of Tampere University's Secure Programming course (COMP.SEC.300).

## Structure of the Program
The program consists of three main parts, which are command line interface (CLI), dependency vulnerability analysis and static code analysis.


## Support
Currently supports only Gradle and Maven projects. 

## Purpose
Project scans given project folder, finds dependency jar-file locations. <br>
If `scanForVulnerabilities` is configured, uses https://nvd.nist.gov/vuln database to fetch vulnerabilities <br>
while also providing basic static code analysis for the project.

## Running
1. Download and extract the project

2. Start the program by running it in your environment using `java -jar secure-programming-course-project.jar`. <br>
After running the program input the project folder or jar-file which is to be analyzed. <br>
If you want to customize the tasks please refer to the [Configuring](#configuring-chapter) chapter. 

### Configuring
This program includes dependencyScanner.properties file, which has modifiable values to customize the tasks of the program. <br>
Changing the value of scanForVulnerabilities value to true, the given project or jar file is searched for vulnerabilities using the <br> NVD vulnerability database (https://nvd.nist.gov/vuln). It is important to provide an NVD API key if you plan on using the NVD <br> vulnerability database in order to avoid errors and in order to not hit the request limits of the API. <br><br>
*A new NVD API key can be requested at https://nvd.nist.gov/developers/request-an-api-key*


## Technologies
This section contains technologies and libraries used in the program.

### Testing
This project is tested using unit test cases and Gradle plugins, such as Spotbugs. The original plan was to use a custom pipeline for testing, but due to the limited time and errors encountered during the development, the testing is done only by hand and by using the previously mentioned Gradle plugin. 

#### Spotbugs
Spotbugs is used for static code analysis. Spotbugs is an open source library with LGPL-2.1 license. <br>
Spotbugs is available at https://spotbugs.github.io/. <br>

### Testcases

#### 1. Broken Access Control
This program is accessible for everyone. That is why broken access control is not considered to be part of testing.

#### 2. Security Misconfiguration
Test cases include a test, which tests that using certain configuration in `dependencyScanner.properties` results in a certain features to be used.

#### 3. Software Supply Chain Failures
For vulnerable libraries the dependency-check Gradle plugin can be used to check this project. First run a gradle task `gradle copydependencies` and after that run `gradle dependencycheckanalyze` in order to produce a vulnerablity report. 

#### 4. Cryptographic Failures
This program is not using any cryptography algorithms and does not store information in a database.

#### 5. Injection
Injection could be a valid issue in this program, which is prevented by allowing only existing project locations and files to be used as the input value for the scanner. 

#### 6. Insecure Design
This program contains unit test, which are used to ensure correct input validation and to validate that the critical flows are resistant to threats. <br><br>
This project is built in a way that exceptions and errors are caught and handled gracefully in order to not leak any sensitive information outside of the program. <br><br>
This project is checked against vulnerability database to avoid critical vulnerabilities. Spotbugs Gradle plugin is used to ensure that the code itself contains the minimal amount of bugs and coding inconsistencies. 

