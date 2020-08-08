Feature: User Story 37
@37
  Scenario: Getting The Office Titles
    Given User login to HRMS application
    And Navigate to Edit Users
    And Navigate to Job and click on Edit
    When Open Office Location Drop down
    Then All the values in the Database should be available in Drop down
   