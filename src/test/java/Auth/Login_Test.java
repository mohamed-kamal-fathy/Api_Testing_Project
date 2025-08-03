package Auth;

import BaseTest.BaseTest;
import models.User;
import org.slf4j.Logger;
import org.testng.annotations.Test;
import utils.LoggerUtil;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class Login_Test extends BaseTest {

    private static final Logger log = LoggerUtil.getLogger(Login_Test.class);

    @Test
    public void user_login_test(){

        log.info("Starting the test (user_login_test)...");
        User loginUser = new User("mor_2314", "83r5^_");

        given()
                .header("Content-Type","application/json")
                .body(loginUser)
        .when()
                .post("auth/login")
        .then()
                //.log().all()
                .assertThat().statusCode(200)
                .time(lessThan(3000L));

    }

}
