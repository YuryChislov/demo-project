# web-automation for 'http://cafetownsend-angular-rails.herokuapp.com/'

## Requirements and Setup

### Basic Setup from Intellij

##### To run auto-tests from this project the following is required
1. Java 10/11 has been installed in the machine you would like to run tests.
2. JAVA_HOME is set on your machine. (Instruction for this: 'https://docs.oracle.com/cd/E19509-01/820-3208/inst_cli_jdk_javahome_t/')
3. At least one of the following browsers are installed: Chrome, Internet Explorer, Firefox. Ideally to have installed all three. This would allow to run test in three different browsers.

#### To clone this repository using Intellij
1. Before you import, you need to make sure that in Intellij’s project defaults you have JDK 10/11 setup as an SDK properly.
2. Clean up your existing folder or create a new one for this project.
3. File -> New -> Project from Version Control -> Git
4. Specify this repository URL 'https://github.com/yury-chislov/demo-project' and specify the path where to clone this Project and click "clone"
4. When Project is cloned IJ asks you if you want to open the project say yes.
5. It should then instantly open and show you this message in the corner: "Unlinked Gradle project? Import Gradle project".
6. Clink import, a Window "Import Module from Gradle" opens. Tick boxes "Use auto-import", "Create separate module per source set" and click on "Use default gradle wrapper" radiobutton. In Gradle JVM box choose your JDK.
7. When you open any *java file in "tests" package, if it complains that there’s no ProjectSDK set. Click the link and fix it.
8. In the version control window in IJ you should now have the project with NO changes, NO added or deleted files etc. IF it shows any changes, do not proceed and try to clone the repository to the new directory.

#### To clone this repository using terminal:
This option assumes that you are familiar with GitHub and git clone so just do as you will do normally and clone the 'https://github.com/yury-chislov/demo-project.git' repository in your machine.

### Downloading WebDrivers

- For testing with Chrome browser get Chrome driver for your platform from: https://sites.google.com/a/chromium.org/chromedriver/downloads . Do *NOT* commit this to the repository, it has to live outside of the codebase in a location of your choice.

- For testing with IE browser (for Windows) Get IE driver server from: http://selenium-release.storage.googleapis.com/index.html?path=3.9/ . Do *NOT* commit this to the repository, it has to live outside of the codebase in a location of your choice.
*for x64 windows system it is still recommended to use IEDriverServer_Win32. Driver x64 works unstable.

- For testing with Firefox browser get geckodriver for your platform from: https://github.com/mozilla/geckodriver/releases . Do *NOT* commit this to the repository, it has to live outside of the codebase in a location of your choice.

*NOTE:* WebDrivers **must** match the version of webBrowser on the PC.
        To successfully run test with IE browser your IE web browser security setting has to be changed to low security level and " enable protected mode" should be unticked.

### Create local.properties file

Create a file **local.properties** in the root of the project (https://github.com/yury-chislov/demo-project/blob/master/local.properties.sample). It needs to:
- Contain the location to the binary of your WebDriver(s). 
_Example:_ `chromeDriverBinaryPath=/home/yury/.local/bin/chromedriver`. You can use local.properties.sample (which IS part of the repository) as a template.
- Contain the location to folder with test screenshots and ERROR_log file:
_Example:_ `screenshotsPath=C:/Users/yury/AutoScreenshots/`

## How to run tests in Intellij

1. Right click on one of the folowing files in folder `com/mobiquity/testing/webautomation/base/`
    * `chromeBrowserTest.xml` if you wish to run tests only with chrome browser
    * `firefoxBrowserTest.xml` if you wish to run tests only with firefox browser
    * `IEBrowserTest.xml` if you wish to run tests only with IE browser
    * `corssBrowserTest.xml` if you wish to run tests with all three browsers
2. Select `Run '.../...BrowserTest.xml'`

You also can specify in the files above what test goups/classes you want to run you you dont wan to run all test suite.
    
   For execution particular group of tests in tag `<test>/<groups>/<run>/<include>` specify the group you want to run. (For example: `<include name = "login" />`)
    
   For execution specified test(s) include only `<include name = "myTest" />` and assign `myTest` group to this test(s) in `tests` package. 
    
   _Example:_ 
   `@Test(groups = {"myTests"})`
    
   After finish execution delete "myTest" group.
   
*NOTE:* If the test has `dependsOnGroups` annotation it won't run before the specified group successfully complete
    
   _Example:_
   `@Test(groups = {"editEmployee", "mainPage"}, description = "Main page. Test Edit Employee from the list", dependsOnGroups = "createEmployee")` has **'dependsOnGroups = "createEmployee"'** and will be executed after test which belongs to group `createEmployee`
    
   For execution all tests put in `<run>` tag `<include name="all">`

## How to run tests using command line

1. cd to the project directory `cd <your local path to the project>`
1. Run `gradlew clean`. This cleans up all build and output directories.
2. Run a defined test suite.

    _Example:_

    * `./gradlew test -PchromeBrowserSuite` to run tests with Chrome browser
    * `./gradlew test -PfirefoxBrowserSuite` to run tests with Firefox browser
    * `./gradlew test -PIEBrowserSuite`       to run tests with IE browser
    * `./gradlew test -PcrossBrowserSuite`     to run tests with all three browsers
    
    It is highly recomended to run with `--info` to have all passing test steps and another useful information in your console!
 
 *NOTE* For Windows console gradlew command runs without `./`
    
    _Example:_ **`gradlew test -PchromeBrowserSuite --info`**
    
    *NOTE* The first time you run the test suite from the console, it may take some time, because it will download and compile all the necessary components.

## Process to create Allure report

1. Run Allure report creation: `gradlew allureReport`
    This will create test report information in the following directories:
    ```
    build/allure-results
    build/reports
    build/test-results
    ```
2. Serve up the Allure report in a browser: `gradlew allureServe` It will open the browser with the UI test report.
