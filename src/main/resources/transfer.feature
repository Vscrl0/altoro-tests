#All examples must be in String format
#Valid accounts are of the form "username_ACCOUNT". e.g. "admin_CORPORATE", "jsmith_CREDIT_CARD".

Feature:Transfer of funds

  Scenario Outline: Successful transfer
    Given I am logged in
    And I am at the transfer funds page
    When I transfer amount <amount> from account <fromAccount> to account <toAccount>
    Then I will receive a valid success message
    Examples:
      | amount | fromAccount       | toAccount         |
      | "0.01" | "admin_CHECKING"  | "admin_CORPORATE" |
      | "2"    | "admin_CHECKING"  | "admin_CORPORATE" |
      | "+1"   | "admin_CHECKING"  | "admin_CORPORATE" |
      | "+1.0" | "admin_CORPORATE" | "admin_CHECKING"  |

  Scenario Outline: Failed transfer
    Given I am logged in
    And I am at the transfer funds page
    When I transfer amount <amount> from account <fromAccount> to account <toAccount>
    Then I will receive an error alert
    Examples:
      | amount  | fromAccount       | toAccount         |
      | "-0.01" | "admin_CHECKING"  | "admin_CORPORATE" |
      | "0"     | "admin_CHECKING"  | "admin_CORPORATE" |
      | ""      | "admin_CORPORATE" | "admin_CHECKING"  |
      | "+0.01" | "admin_CHECKING"  | "admin_CHECKING"  |