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

    @Given("I am logged in as {string}")
    public void iAmLoggedInAs(String user) {
        String helloText;
        String username;
        String password;
        Pages.myAccount().goTo();
        switch(user){
            case "admin": {
                helloText = "Hello Admin User";
                username = "admin";
                password = "admin";
                break;
            }
            case "jsmith": {
                helloText = "Hello John Smith";
                username = "jsmith";
                password = "Demo1234";
                break;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
        if (Pages.navBar().isLoggedIn()){
            if(!Pages.myAccount().getHelloText().equals(helloText)){ //check assumes uniqueness of account name
                Pages.loginPage().goTo();
                Pages.loginPage().signIn(username,password);
            }
        }else{
            Pages.loginPage().signIn(username,password);
        }
    }

    @Then("I will receive an error alert")
    public void iWillReceiveAnErrorAlert() {
        Assert.assertTrue(Browser.isAlertPresent());
    }
}
