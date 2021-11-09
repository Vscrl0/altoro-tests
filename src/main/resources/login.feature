#All usernames, password, and roles must be Strings.
#Valid roles are admin and user.
Feature:Login

  Scenario Outline:Successful login
    Given I am at the login page
    When I login using username <username> and password <password>
    Then I will be logged in as <role>
    Examples:
      | username |  | password   |  | role    |
      | "admin"  |  | "admin"    |  | "admin" |
      | "jsmith" |  | "Demo1234" |  | "user"  |

  Scenario Outline:Login failure
    Given I am at the login page
    When I login using username <username> and password <password>
    Then The login will fail
    Examples:
      | username |  | password |
      | "junk"   |  | "junk"   |
      | ""       |  | ""       |