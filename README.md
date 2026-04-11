# Java Dependency and Vulnerability scanner <br>(Maven and Gradle projects)

This project is done as a part of Tampere University's Secure Programming course (COMP.SEC.300).

## Structure of the Program
The program consists of three main parts, which are command line interface (CLI), dependency vulnerability analysis and static code analysis.<br><br>
Command line interface (CLI) is accepting two kinds of commands: 
1. Path to the project or jar file to be scanned. E.g. `C:/custom-project` or `jar-file.jar`.
2. `Stop`, which is used to shutdown the program.
3. `Help`, which displays help for the program.

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
*A new NVD API key can be requested at [NVD API key](https://nvd.nist.gov/developers/request-an-api-key)*
