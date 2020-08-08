@sprint2
Feature: User story 33


 
  Scenario: User story 33
    Given a JWT is generated
    Given I prepare a put call to update an existing employee with employee ID "23140A"
    And I make a put call to update the employee
    When I get information on the updated employee
    Then I need to be able to verify that the retrieved data matches the input
     | emp_firstname | emp_middle_name | emp_lastname | emp_birthday | emp_gender | emp_job_title  | emp_status |
     | Abuzer        | Kulyutmaz       | Kilkuyruk    | 2000-01-01   | Male       | Cloud Architect| Employee   |

  