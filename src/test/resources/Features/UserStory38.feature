Feature: User Story 38

  @38
  Scenario: Getting The Job Titles
    Given I log in to HRMS
    And go to Employee Search page
    When I get the Job Titles as a list
    And I get the Job titles from the database
    Then I validate the list from UI against DB
