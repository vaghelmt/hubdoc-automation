# hubdoc-automation

Project Description:
This repo incorporates end to end automated tests for important hubdoc user flows

Tech Stack:
It uses Selenium WebDriver with Java bindings to automate the application. It makes use of AutoIT to handle Windows Explorer during file upload.

I have used testNG framework to structure the tests and Maven for building and packaging the project.

For validations, I have used Hamcrest Matchers which in my opinion provides very comprehensive matchers.

The project includes WebDriverManager so there is no need to manage the driver executables seperately. Depending upon your the version of browsers on your system, it will automatically download the drivers.

Note: Please note that this framework doesnot support parallel execution at this point. The support might be added in future.

Prequisites:
1. Maven installed on the machine where you are planning to run the tests
2. Access to hubdoc website. If you do not have the access, please go to the site - https://app.hubdoc.com/login - and create a new account.

How to use:
PLease follow these steps to set up download and run the tests on your machine:

1. Clone the project using this command - git clone ""
2. Change the directory using this command - cd hubdoc-automation
3. Update the config.properties file user src/test/resources folder with username and password
4. Execute this command - mvn test
5. The result file will be generated under target/surefire-reports directory. You can either open index.html or emailable-report.html to view the results

Tip: If you want to run the tests in a CI/CD pipeline, create a Jenkins free-style job and select execution environment as shell. After that use the same command as above and you will have your jenkins job is almost ready. Next, you need to create a dependency with the hubdoc application job and then whenever there is a new hubdoc build available, these tests will run automatically.

If you have any queries or suggestions, please reach out to me at mitul.vaghela@gmail.com. If you would like to contribute to this project, please raise a PR.


