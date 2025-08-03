package Carts;

import Auth.Login_Test;
import BaseTest.BaseTest;
import org.slf4j.Logger;
import org.testng.annotations.Test;
import models.Cart;
import models.ProductInCart;
import utils.LoggerUtil;

import java.util.Collections;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Carts_Test extends BaseTest {

    private static final Logger log = LoggerUtil.getLogger(Carts_Test.class);
    @Test
    public void get_all_carts(){

        log.info("Starting the test (get_all_carts)...");
          when()
                .get("carts")
          .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("[0].id", equalTo(1))
                .assertThat().body("[0].products[0].productId", equalTo(1))
                .time(lessThan(3000L))
                .assertThat().body("size()", equalTo(7));
    }
    @Test
    public void get_a_single_cart(){

        log.info("Starting the test (get_a_single_cart)...");

        when()
                .get("carts/5")
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("id", equalTo(5))
                .time(lessThan(3000L));
    }
    @Test
    public void limit_results_of_carts() {
        log.info("Starting the test (limit_results_of_carts)...");
        given()
                .queryParam("limit", 5)
        .when()
                .get("/carts")
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("[4].id", equalTo(5))
                .time(lessThan(3000L))
                .assertThat().body("size()", equalTo(5));
    }
    @Test
    public void Sort_result_of_carts_DESC() {
        log.info("Starting the test (Sort_result_of_carts_DESC)...");
        given()
                .queryParam("sort", "desc")
        .when()
                .get("carts")
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("[0].id", equalTo(7))
                .assertThat().body("[6].id", equalTo(1))
                .time(lessThan(3000L))
                .assertThat().body("size()", equalTo(7));;

    }
    @Test
    public void get_carts_in_specific_date() {
        log.info("Starting the test (get_carts_in_specific_date)...");
        given()
                .queryParam("enddate", "2020-01-01")
        .when()
                .get("/carts")
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .time(lessThan(3000L))
                .body("size()", equalTo(1))
                .assertThat().body("[0].date", equalTo("2020-01-01T00:00:00.000Z"));
    }
    @Test
    public void get_carts_in_a_date_range() {
        log.info("Starting the test (get_carts_in_a_date_range)...");
        given()
                .queryParam("startdate", "2019-01-01")
                .queryParam("enddate", "2020-01-02")
        .when()
                .get("carts")
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().time(lessThan(3000L))
                .body("[0].date", equalTo("2020-01-02T00:00:00.000Z"))
                .body("[1].date", equalTo("2020-01-01T00:00:00.000Z"));
    }
    @Test
    public void get_carts_in_specific_user() {
        log.info("Starting the test (get_carts_in_specific_user)...");
        when()
                .get("/carts/user/2")
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .time(lessThan(3000L))
                .body("size()", equalTo(1))
                .assertThat().body("[0].userId", equalTo(2));
    }
    @Test
    public void add_a_new_product_in_carts() {
        log.info("Starting the test (add_a_new_product_in_carts)...");
        ProductInCart product = new ProductInCart(1, 2);
        Cart cart = new Cart();
        cart.setUserId(5);
        cart.setDate("2020-03-03T00:00:00.000Z");
        cart.setProducts(Collections.singletonList(product));


        given()
                .header("Content-Type","application/json")
                .body(cart)
        .when()
                .post("carts")
        .then()
                .log().all()
                .assertThat().body("id", equalTo(11))
                .assertThat().statusCode(200)
                .time(lessThan(3000L));
    }
    @Test
    public void update_a_product_PUT() {
        log.info("Starting the test (update_a_product_PUT)...");
        Cart cart = new Cart();
        cart.setUserId(8);
        cart.setDate("2020-03-03T00:00:00.000Z");

        given()
                .contentType("application/json")
                .body(cart)
        .when()
                .put("/carts/5")
        .then()
                .assertThat().statusCode(200)
                .assertThat().body("userId", equalTo(8))
                .assertThat().body("id",equalTo(5))
                .time(lessThan(3000L));
    }
    @Test
    public void update_a_product_using_PATCH() {
        log.info("Starting the test (update_a_product_using_PATCH)...");
        Cart cart = new Cart();
        cart.setUserId(8);
        cart.setDate("2020-03-03T00:00:00.000Z");

        given()
                .contentType("application/json")
                .body(cart)
        .when()
                .patch("/carts/5")
        .then()
                .assertThat().statusCode(200)
                .assertThat().body("userId", equalTo(8))
                .assertThat().body("id",equalTo(5))
                .time(lessThan(3000L));
    }
    @Test
    public void delete_a_specific_cart() {
        log.info("Starting the test (delete_a_specific_cart)...");

        when()
                .delete("/carts/5")
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .body("id", equalTo(5))
                .time(lessThan(5000L));;
    }
}
