# Introduction (Explanation and thoughts)

## Key Concepts

* Selenium webDriver is used to drive the tests.
* The tests are structured using the Page Object Model (POM).

### Drive tests with Selenium webDriver

In order to run tests on against website in this project I use Selenium webDriver. You can find out more information about Selenium on their official website: https://www.selenium.dev/


### Organize Code with the Page Object Model
In order to make tests more readable and maintainable, in this project I applied Page Object Model (POM) pattern. The tests themselves are really easy to read and maintain.
The project has three main folders:
* **base** - this folder contains all the main settings for the tests:
    * **CafetownsendSite** file contains test data that we are going to use in the tests, and defines the pages on which our test will be run.
    * Xml files in this folder are config files where I specify the exact test we want to run, and the browsers in which it will run. 
    
    *NOTE** IE webDriver has a poor performance. If to run test suite with IE browser it will take more time than it takes with another browsers. This is just a quality of IE webdriver. Also **DeleteEmployee** test will be failed which indicated a bug with the layout on the main page when to open in IE.
    * **TestBase** file is the file which is responsible to all setUps and tearDowns. It also contains methods that will be used in multiple tests regardless on what webpages they are executing.
    
* **pageobjects** - this folder contains the files for testing web pages. Each page contains three main sections:
    * **Objects** where all the web elements are defined
    * **Actions** where the actions with the web elements were created
    * **Checkers** - the methods that check the actual result matches to expected result after certain actions on the page.
    
* **tests** - this  folder contains all created tests we can run. Each test is represented as a separate class. 

All test steps are described using test annotations and output to the console. This helps you get great test reports wherever testing is done from the IDE or the command line.     


#### Main test features
##### Error logging
In this project I created the method which checks the browser console to errors. From my experience it happens often when the auto test passed and zero errors detected, however if you open browser console you can see logged errors that might be critical. Not necessarily these errors related to running tests but they might be very valuable to report or look into them.
After running these tests by myself the system logged the console error I would probably look into in real case.
##### Screenshots
Also I created the method which allows to easily make the screenshots when requires and it will make the screenshot automatically in failure case. Error screenshots allow to find the issue much quicker.  
     

### Test cases
Since I was limited in time when I was implementing this project, and I did not have enough documentation with the requirements, I used Exploratory testing and a heuristic approach. I selected some of the most important functions in my opinion that are vital for this website, and created my own test for them:
* Test successful login
* Tst unsuccessful login
* Test Create Employee
* Test Edit Employee from the list
* Test Delete Employee from the list

The last three test cases are dependent to each other. "Edit Employee" test will run only if "Create Employee" test was successful and created the instance for the second test. And "Delete Employee" test will run only if "Edit employee" test complete successfully. This helps to avoid flooding Data Base and makes tests independent on current data on the website.

Please find out more information about test cases here: [TestCases](https://github.com/yury-chislov/demo-project/blob/master/docs/TestCases.md)


