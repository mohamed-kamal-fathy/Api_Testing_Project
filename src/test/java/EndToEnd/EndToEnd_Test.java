package EndToEnd;

import BaseTest.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.*;
import org.slf4j.Logger;
import org.testng.annotations.Test;
import utils.LoggerUtil;

import java.util.Collections;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class EndToEnd_Test extends BaseTest {
    private static final Logger log = LoggerUtil.getLogger(EndToEnd_Test.class);

    @Description("Creates user -> product -> cart -> deletes them")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void E2E_Flow() {

        log.info(" Starting End-to-End Flow test");
        Name name = new Name("Mohamed", "Kamal");
        User user = new User("e2euser@example.com", "e2euser", "123456", name, "0100000000");
        // Create a new user
        Response createUser = given()
                .header("Content-Type", "application/json")
                .body(user)
        .when()
                .post("/users")
        .then()
                .log().body()
                .assertThat().statusCode(200)
                .time(lessThan(3000L))
                .extract().response();

        int userId = createUser.path("id");

        // Create a new product
        Product product = new Product(
                "E2E Product",
                99.99,
                "End to End test product",
                "https://i.pravatar.cc",
                "electronics");

        Response createProduct = given()
                .header("Content-Type", "application/json")
                .body(product)
        .when()
                .post("/products")
        .then()
                .log().body()
                .assertThat().statusCode(200)
                .time(lessThan(3000L))
                .body("title", equalTo("E2E Product"))
                .extract().response();

        int productId = createProduct.path("id");

        // Create a new cart
        ProductInCart cartProduct = new ProductInCart(productId, 1);
        Cart cart = new Cart(userId, "2025-07-27", Collections.singletonList(cartProduct));

        Response createCart = given()
                .header("Content-Type", "application/json")
                .body(cart)
        .when()
                .post("/carts")
        .then()
                .log().body()
                .assertThat().statusCode(200)
                .time(lessThan(3000L))
                .body("userId", equalTo(userId))
                .body("products[0].productId", equalTo(productId))
                .extract().response();

        int cartId = createCart.path("id");



        // Delete cart
        when()
                .delete("/carts/" + cartId)
        .then()
                .log().body()
                .assertThat().statusCode(200)
                .time(lessThan(3000L));

        // Delete product
        when()
                .delete("/products/" + productId)
        .then()
                .log().body()
                .assertThat().statusCode(200)
                .time(lessThan(3000L));

        // Delete user
        when()
                .delete("/users/" + userId)
        .then()
                .log().body()
                .assertThat().statusCode(200)
                .time(lessThan(3000L));
        log.info(" End-to-End Flow test completed successfully");
    }
}
