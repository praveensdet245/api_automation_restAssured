package test;

import dev.restfil.api.common.utils.RestMethods;
import dev.restfil.api.common.utils.RestUtils;
import dev.restfil.api.common.utils.Services;
import dev.restfil.api.models.ObjectClass;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestClass {

    private static final Logger log = LoggerFactory.getLogger(TestClass.class);

    private RestUtils restUtils;
    private Response response;
    private ObjectClass objectClass;

    @BeforeTest
    public void initiateService() {
        restUtils = new RestUtils();
        restUtils.initiateService(Services.TEST_SERVICE,"objects");
    }
    @Test
    public void test_createObject() {
        response = restUtils.executeAPI(RestMethods.POST,null, ObjectClass.generateFakeObject());
        objectClass = restUtils.assignResponseToClass(response,ObjectClass.class);
        log.info("test response ::: ",objectClass.toString());
        Assert.assertTrue(restUtils.isResponseCodeExpected(200),"response code is not 200");
    }

    @Test(dependsOnMethods = {"test_createObject"})
    public void test_validateObjectId() {
        Assert.assertNotNull(objectClass.getId(),"id is null");
    }
}
