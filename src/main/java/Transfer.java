import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


public class Transfer {
    private String transferSuccess;
    private String amount;

    @Given("I am at the transfer funds page")
    public void iAmAtTheTransferFundsPage() {
        Pages.transferPage().goTo();
    }

    @When("I transfer amount {string} from account {string} to account {string}")
    public void iTransferAmountFromAccountToAccount(String amount, String fromAccount, String toAccount) {
        Pages.transferPage().transfer(amount, Account.selectAccount(fromAccount), Account.selectAccount(toAccount));
        try {
            Pages.transferPage().getSuccessMessage();
            this.amount = amount;
            transferSuccess = " was successfully transferred from Account " + Account.selectAccount(fromAccount).getId() + " into Account " + Account.selectAccount(toAccount).getId();
        } catch (Exception ignored) {

        }
    }

    @Then("I will receive a valid success message")
    public void iWillReceiveASuccessMessage() {
        String amountSuccess = (Pages.transferPage().getSuccessMessage() + " ").split(" ")[0];
        Assert.assertTrue(Pages.transferPage().getSuccessMessage().contains(transferSuccess) && Double.parseDouble(amountSuccess) == Double.parseDouble(amount));

    }

    @Given("I am logged in")
    public void iAmLoggedIn() {
        Pages.homePage().goTo();
        if (!Pages.navBar().isLoggedIn()) {
            Pages.loginPage().goTo();
            Pages.loginPage().signIn("admin", "admin");
        }
    }

    @Then("I will receive an error alert")
    public void iWillReceiveAnErrorAlert() {
        Assert.assertTrue(Browser.isAlertPresent());
    }
}
