#All examples must be in String format
#Valid integer amounts must end in .0 (e.g. 5 should be 5.0)
#Valid accounts are "CORPORATE" and "CHECKING".

Feature:Transfer of funds

  Scenario Outline: Successful transfer
    Given I am logged in
    And I am at the transfer funds page
    When I transfer amount <amount> from account <fromAccount> to account <toAccount>
    Then I will receive a valid success message
    Examples:
      | amount | fromAccount | toAccount   |
      | "0.01" | "CHECKING"  | "CORPORATE" |
      | "2"    | "CHECKING"  | "CORPORATE" |
      | "+1"   | "CHECKING"  | "CORPORATE" |
      | "+1.0" | "CORPORATE" | "CHECKING"  |

  Scenario Outline: Failed transfer
    Given I am logged in
    And I am at the transfer funds page
    When I transfer amount <amount> from account <fromAccount> to account <toAccount>
    Then I will receive an error alert
    Examples:
      | amount  | fromAccount | toAccount   |
      | "-0.01" | "CHECKING"  | "CHECKING"  |
      | "0"     | "CHECKING"  | "CORPORATE" |
      | ""      | "CORPORATE" | "CHECKING"  |