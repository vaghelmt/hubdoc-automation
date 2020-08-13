# hubdoc-automation

## Project Description

This repo incorporates end to end automated tests for important hubdoc user flows. At this point, limited number of test cases are automated. You can find all the test cases documented here - https://docs.google.com/spreadsheets/d/1N48AKhEXfMMU5Ut0cO15cTW-zkertenoR3tQ_OkeKqU/edit?usp=sharing

## Tech Stack

It uses Selenium WebDriver with Java bindings to automate the application. It makes use of AutoIT to handle Windows Explorer during file upload.

I have used testNG framework to structure the tests and Maven for building and packaging the project.

For validations, I have used Hamcrest Matchers which in my opinion provides very comprehensive matchers.

The project includes WebDriverManager so there is no need to manage the driver executables seperately. Depending upon your the version of browsers on your system, it will automatically download the drivers.

For reporting, this framework uses Extent Reports inaddition to the default maven surefire reports

Note: Please note that this framework doesnot support parallel execution at this point. The support might be added in future.

## How to use

### Prequisites

1. Java JDK 8.0 or above
2. Maven installed on the machine where you are planning to run the tests
3. Access to hubdoc website. If you do not have the access, please go to the site - https://app.hubdoc.com/login - and create a new account.

### Run Instructions:

#### Command Line

PLease follow these steps to download and run the tests on your machine through CLI:

1. Clone the project using this command - git clone "https://github.com/vaghelmt/hubdoc-automation.git"
2. Change the directory using this command - cd hubdoc-automation
3. Update the config.properties file under src/test/resources folder with username and password
4. In the same file, also update the browser and implicit/explicit wait properties
4. Execute this command - mvn test
5. The default result file will be generated under target/surefire-reports directory. You can either open index.html or emailable-report.html to view the results
6. In addition, Extent Report will also be generated with the name ExtentReportResults.html in the root directory

Tip: If you want to run the tests in a CI/CD pipeline, create a Jenkins free-style job and select execution environment as shell. After that use the same command as above and you will have your jenkins job is almost ready. Next, you need to create a dependency with the hubdoc application job and then whenever there is a new hubdoc build available, these tests will run automatically.

#### Eclipse
PLease follow these steps to set up and run the tests on your eclipse:

1. Clone the project using this command - git clone "https://github.com/vaghelmt/hubdoc-automation.git"
2. Open Eclipse
3. Choose File -> Import
4. Set the directory to hubdoc-automation
5. Press Next and then Finish
6. Update the config.properties file under src/test/resources folder with username and password
7. In the same file, also update the browser and implicit/explicit wait properties
8. Right click one the project root and select run -> maven test
9. Alternatively, right click on testng.xml and run as TestNG
10.Extent Report will also be generated with the name ExtentReportResults.html in the root directory


#### IntelliJ
PLease follow these steps to set up and run the tests on your intelliJ:

1. Clone the project using this command - git clone "https://github.com/vaghelmt/hubdoc-automation.git"
2. Open IntelliJ Idea Community
3. Choose File -> Open
4. Navigate to hubdoc-automation
5. Choose Ok
6. Wait for set up to complete. You can track the progress in status bar at the bottom
7. Update the config.properties file under src/test/resources folder with username and password
8. In the same file, also update the browser and implicit/explicit wait properties
9. On the right hand side panel, click on maven, expand the LifeCycle folder and click test
10.Extent Report will also be generated with the name ExtentReportResults.html in the root directory


If you have any queries or suggestions, please reach out to me at mitul.vaghela@gmail.com. If you would like to contribute to this project, please raise a PR.


