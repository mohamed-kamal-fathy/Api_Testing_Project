package Users;

import BaseTest.BaseTest;
import models.Name;
import models.User;
import org.slf4j.Logger;
import org.testng.annotations.Test;
import utils.LoggerUtil;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Users_Test extends BaseTest {

    private static final Logger log = LoggerUtil.getLogger(Users_Test.class);

    @Test
    public void get_all_users() {
        log.info("Starting test: get_all_users");

        when()
                .get("users")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .time(lessThan(3000L))
                .assertThat().body("size()", equalTo(10));
    }

    @Test
    public void get_a_single_user() {
        log.info("Starting test: get_a_single_user");

        when()
                .get("users/2")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("id", equalTo(2))
                .time(lessThan(3000L));
    }

    @Test
    public void limit_results_of_users() {
        log.info("Starting test: limit_results_of_users");

        given()
                .queryParam("limit", 5)
                .when()
                .get("/users")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .time(lessThan(3000L))
                .assertThat().body("size()", equalTo(5));
    }

    @Test
    public void Sort_result_of_users_DESC() {
        log.info("Starting test: Sort_result_of_users_DESC");

        given()
                .queryParam("sort", "desc")
                .when()
                .get("users")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("[0].id", equalTo(10))
                .assertThat().body("[9].id", equalTo(1))
                .time(lessThan(3000L))
                .assertThat().body("size()", equalTo(10));
    }

    @Test
    public void add_a_new_user() {
        log.info("Starting test: add_a_new_user");

        Name name = new Name("Mohamed", "Kamal");
        User user = new User("newuser@example.com", "newuser", "123456", name, "0100000000");

        given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post("carts")
                .then()
                .log().all()
                .assertThat().body("id", equalTo(11))
                .assertThat().statusCode(200)
                .time(lessThan(3000L));
    }

    @Test
    public void update_a_user_using_PUT() {
        log.info("Starting test: update_a_user_using_PUT");

        Name name = new Name("Updated_with_PUT", "User");
        User user = new User("updated@example.com", "updatedUser", "987654", name, "0123456789");

        given()
                .contentType("application/json")
                .body(user)
                .when()
                .put("/users/2")
                .then()
                .assertThat().statusCode(200)
                .time(lessThan(3000L));
    }

    @Test
    public void update_a_user_using_PATCH() {
        log.info("Starting test: update_a_user_using_PATCH");

        Name name = new Name("Updated_with_PATCH", "User");
        User user = new User("updated@example.com", "updatedUser", "987654", name, "0123456789");

        given()
                .contentType("application/json")
                .body(user)
                .when()
                .patch("/users/2")
                .then()
                .assertThat().statusCode(200)
                .time(lessThan(3000L));
    }

    @Test
    public void delete_a_specific_cart() {
        log.info("Starting test: delete_a_specific_cart");

        when()
                .delete("/users/2")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .body("id", equalTo(2))
                .time(lessThan(3000L));
    }
}
