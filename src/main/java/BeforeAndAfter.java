import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;

public class BeforeAndAfter {
    @BeforeAll
    public static void setBrowser() {
        Browser.useFirefox();
    }

    @AfterAll
    public static void cleanUp() {
        Browser.quit();
    }
}
