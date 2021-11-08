import io.cucumber.java.After;
import io.cucumber.java.Before;

public class BeforeAndAfter {
    @Before
    public void setBrowser() {
        Browser.useFirefox();
    }
    @After
    public void cleanUp() {
        Browser.quit();
    }
}
