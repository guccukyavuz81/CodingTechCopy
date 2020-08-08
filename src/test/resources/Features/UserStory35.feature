@sprint2

Feature: User Story 35 Making Sure Employee ID is Uniqe to Coding Tech

   
   

  Scenario: Verifying that an Employee ID is unique to an employee
  Given a JWT is generated
    And A request is prepared to create an employee
    And A POST call is made to create an employee
    Then The status code for creating an employee is 201
    And The employee is created and response contains key "Message" and value "Entry Created"
    Given The employee ID "Employee[0].employee_id" is stored as a global variable to be used for other calls
    When Storing response employeeID into empID
    Then Verifying that globally stored ID is matching the exact response ID
    And Update created employee using the stored global empID
    Then Partially update the updated employee using the same global ID
    Then Delete the partially updated employee using the same global ID
