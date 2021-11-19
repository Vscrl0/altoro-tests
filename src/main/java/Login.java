import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.AfterClass;
import org.junit.Assert;

public class Login {
    @Given("I am at the login page")
    public void iAmAtTheLoginPage() {
        Pages.loginPage().goTo();
    }

    @When("I login using username {string} and password {string}")
    public void iLoginUsingUsernameAndPassword(String username, String password) {
        Pages.loginPage().signIn(username, password);
    }

    @Then("I will be logged in")
    public void iWillBeLoggedIn() {
        Assert.assertTrue(Pages.navBar().isLoggedIn());
    }

    @Then("The login will fail")
    public void theLoginWillFail() {
        Assert.assertTrue(Pages.loginPage().isFailedLogin());
    }
}
