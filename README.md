# Java Dependency and Vulnerability scanner <br>(Maven and Gradle projects)

This project is done as a part of Tampere University's Secure Programming course (COMP.SEC.300).

## Support
Currently supports only Gradle and Maven projects. 

## Purpose
Project scans given project folder, finds dependency jar-file locations. <br>
If `scanForVulnerabilities` is configured, uses https://nvd.nist.gov/vuln database to fetch vulnerabilities <br>
while also providing basic static code analysis for the project.

## Running
1. Download and extract the project
2. Double click dvs.bat

## Technologies
This section contains technologies and libraries used in the program.

#### Spotbugs
Spotbugs is used for static code analysis. Spotbugs is an open source library with LGPL-2.1 license. <br>
Spotbugs is available at https://spotbugs.github.io/. <br>

Lorem ipsum dolor sit amet, consectetur adipiscing elit, 
sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. 
Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
Excepteur sint occaecat cupidatat non proident, 
sunt in culpa qui officia deserunt mollit anim id est laborum.

### Testcases

#### 1. Broken Access Control
This program is accessible for everyone. That is why broken access control is not considered to be part of testing.

#### 2. Security Misconfiguration
Test cases include a test, which tests that using certain configuration in `dependencyScanner.properties` results in a certain features to be used.

#### 3. Software Supply Chain Failures


