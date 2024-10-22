package test;

import dev.restfil.api.common.utils.GlobalMap;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class BaseClass {
    @BeforeSuite
    @Parameters({"ENV"})
    public void prerequisites(String env) {
        GlobalMap.put("ENV",env);
    }
}
