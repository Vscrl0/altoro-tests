#All examples must be in String format
#Valid accounts are of the form "username_ACCOUNT". e.g. "admin_CORPORATE", "jsmith_CREDIT_CARD".

Feature:Transfer of funds

  Scenario Outline: Admin successful transfer
    Given I am logged in as <user>
    And I am at the transfer funds page
    When I transfer amount <amount> from account <fromAccount> to account <toAccount>
    Then I will receive a valid success message
    Examples:
      | user     |  | amount | fromAccount       | toAccount         |
      | "admin"  |  | "0.01" | "admin_CHECKING"  | "admin_CORPORATE" |
      | "admin"  |  | "+1"   | "admin_CHECKING"  | "admin_CORPORATE" |
      | "admin"  |  | "+1.0" | "admin_CORPORATE" | "admin_CHECKING"  |

      | "jsmith" |  | "0.01" | "jsmith_SAVINGS"  | "jsmith_CHECKING" |
      | "jsmith" |  | "+1"   | "jsmith_CHECKING" | "jsmith_SAVINGS"  |
      | "jsmith" |  | "+1.0" | "jsmith_SAVINGS"  | "jsmith_CHECKING" |

  Scenario Outline: Admin failed transfer
    Given I am logged in as <user>
    And I am at the transfer funds page
    When I transfer amount <amount> from account <fromAccount> to account <toAccount>
    Then I will receive an error alert
    Examples:
      | user     |  | amount  | fromAccount          | toAccount            |
      #invalid amounts
      | "admin"  |  | "-0.01" | "admin_CHECKING"     | "admin_CORPORATE"    |
      | "admin"  |  | "0"     | "admin_CHECKING"     | "admin_CORPORATE"    |
      | "admin"  |  | ""      | "admin_CORPORATE"    | "admin_CHECKING"     |
      #fromAccount=toAccount
      | "admin"  |  | "+0.01" | "admin_CHECKING"     | "admin_CHECKING"     |
      | "admin"  |  | "+0.01" | "admin_CORPORATE"    | "admin_CORPORATE"    |

      #invalid amounts
      | "jsmith" |  | "-0.01" | "jsmith_SAVINGS"     | "jsmith_CHECKING"    |
      | "jsmith" |  | "0"     | "jsmith_SAVINGS"     | "jsmith_CHECKING"    |
      | "jsmith" |  | ""      | "jsmith_SAVINGS"     | "jsmith_CHECKING"    |
      #fromAccount=toAccount
      | "jsmith" |  | "0.01"  | "jsmith_CHECKING"    | "jsmith_CHECKING"    |
      | "jsmith" |  | "0.01"  | "jsmith_SAVINGS"     | "jsmith_SAVINGS"     |
      | "jsmith" |  | "0.01"  | "jsmith_CREDIT_CARD" | "jsmith_CREDIT_CARD" |

