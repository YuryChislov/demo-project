# Test cases

## LOGIN. Successful
* Step 1: Check login page is uploaded
* Step 2: Insert login credentials: 'Luke'/'Skywalker'
* Step 3: click login button
* Assertion: Check main page uploaded


## LOGIN. NOT Successful
* Step: Check login page is uploaded
* Step: Insert login credentials: correct login and different variations of incorrct password
    * Very long incorrect password
    * All white space types
    * Attempt to inject SQL validation in password box. _Example:_ SELECT Password FROM Users WHERE Username = 'Luke'
    * Attempt to inject query param in password box
* Step: click login button
* Assertion: Check login unsuccessful


## Main page. Test Create Employee
* Step: log in
* Assertion: Check main page uploaded
* Step: click Create button
* Step: fill out the form correctly
    * First Name: TestName_20200308_202805
    * Last Name: lastNameTest
    * Start Date: 2020-01-01
    * Email: test@email
* Step: Click Add button
* Assertion: Check Employee list is uploaded and has entries
* Assertion: Check Employee list contains added entry

## Main page. Test Edit Employee from the list
* Step: log in
* Assertion: Check main page uploaded
* Assertion: Check Employee list is uploaded and has entries
* Step: click on entry name which was created in createEmployee test
* Step: Click Edit button
* Step: Replace 'First Name' and 'Last Name' in form
* Step: click Update button
* Assertion: Check Employee list is uploaded
* Step: Check editing entry has been changed

## Main page. Test Delete Employee from the list
* Step: log in
* Assertion: Check main page uploaded
* Assertion: Check Employee list is uploaded and has entries
* Step: click on entry name which was created in createEmployee test
* Step: Click Delete button
* Step: confirm deletion
* Step: click 'create' button and 'cancel' to refresh the list
* Assertion: Check Employee list is uploaded
* Step: Check deleting entry has been deleted

