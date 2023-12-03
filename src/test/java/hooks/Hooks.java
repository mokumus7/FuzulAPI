package hooks;

import baseUrl.BaseUrl;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setup() {
        BaseUrl.setup();
    }

}
