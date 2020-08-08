@sprint2

Feature: US32 validate from UI that API web services
  
  Background: 
    Given user able to logged with valid admin credentials
    And user able to navigate to employee list page
    Given a JWT is generated
    When create new employee from API and store employeeID "Employee[0].employee_id" as global variable
    And user enter employeeID and search it and click employee from table


  Scenario: validation of created employee information
  	When user able to see employee information
    Given request is prepared to retrieve the created employee
    When GET call is made to retrieve the created employee
    Then validate employeeID, firstName, middleName, lastName, dateOfBirth, gender, jobTitle and employeeStatus from API response


  Scenario: validation of updated employee information
    When user able to get employee information by using unique employeeID and update information
    Given request is prepared to retrieve the updated employee
    When GET call is made to retrieve the updated employee
    Then validate employeeID, firstName, middleName, lastName, dateOfBirth, gender, jobTitle and employeeStatus from API response
