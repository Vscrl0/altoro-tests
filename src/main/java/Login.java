import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class Login {
    @Given("I am at the login page")
    public void iAmAtTheLoginPage() {
        Pages.homePage().goTo();
        Pages.navBar().clickLogin();
    }

    @When("I login using username {string} and password {string}")
    public void iLoginUsingUsernameAndPassword(String username, String password) {
    Pages.loginPage().signIn(username, password);
    }

    @Then("I will be logged in as {string}")
    public void iWillBeLoggedInAsAdmin(String role) {
        if (role.equals("admin")) {
            Assert.assertTrue(Pages.myAccount().isAdmin());
        }else{
            Assert.assertFalse(Pages.myAccount().isAdmin());
        }
    }
    @Then("The login will fail")
    public void theLoginWillFail() {
        Assert.assertTrue(Pages.loginPage().isFailedLogin());
    }
}
