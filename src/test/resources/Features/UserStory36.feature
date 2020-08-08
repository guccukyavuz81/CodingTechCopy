 @sprint2

Feature: User Stroy 36 Syntax HRMS API End to End Workflow
 
  Description: This feature tests and verifies Syntax HRMS Web Services
  The workflow consists of the following sequential calls

  Background: 
    Given a JWT is generated

  Scenario: Creating an employee
    Given a request is prepared to create an employee
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201
    And the employee is created and response contains key "Message" and value "Entry Created"
    And the employee ID "Employee[0].employee_id" is stored as a global variable to be used for other calls

  Scenario: Retrieving created employee
    Given a request is prepared to retrieve the created employee
    When a GET call is made to retrieve the created employee
    Then the status code for retrieving the created employee is 200
    And the retrieved emplyee ID matches the globally stored employee ID
    And the retrieved data matches the data used to create an employee with employee ID
      | emp_firstname | emp_middle_name | emp_lastname | emp_birthday | emp_gender | emp_job_title | emp_status |
      | ilyas         | middle          | unak         | 2000-07-11   | Male       | CEO           | Worker     |

  Scenario: Updating created employee
    Given a request is prepared to update the created employee
    When a PUT call is made to update the created employee
    Then the status code for putting the created employee is 201

  Scenario: Partially updating created employee
    Given a request is prepared to partially update the created employee
    When a PATCH call is made to partially update the created employee
    Then the status code for putting the partially updated employee is 201

  Scenario: Deleting created employee
    Given a request is prepared to delete the created employee
    When a DELETE call is made to delete the created employee
    Then the status code for deleting the created employee is 201
