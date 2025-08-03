package Products;

import BaseTest.BaseTest;
import models.Product;
import org.slf4j.Logger;
import org.testng.annotations.Test;
import utils.LoggerUtil;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Product_Test extends BaseTest {

    private static final Logger log = LoggerUtil.getLogger(Product_Test.class);

    @Test(description = "Get all products from the website")
    public void get_all_products(){
        log.info("Starting test: get_all_products");
        when()
                .get("products")
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("[0].id", equalTo(1))
                .assertThat().body("[19].id", equalTo(20))
                .time(lessThan(3000L))
                .assertThat().body("size()",equalTo(20));
    }

    @Test
    public void get_a_single_product(){
        log.info("Starting test: get_a_single_product");
        when()
                .get("products/1")
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("id", equalTo(1))
                .time(lessThan(3000L))
                .assertThat().body("rating.rate", equalTo(3.9f));
    }

    @Test
    public void get_all_categories() {
        log.info("Starting test: get_all_categories");
        when()
                .get("/products/categories")
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("size()", equalTo(4))
                .assertThat().body("", hasItems("electronics", "jewelery", "men's clothing", "women's clothing"))
                .time(lessThan(3000L));
    }

    @Test
    public void get_product_in_specific_categories() {
        log.info("Starting test: get_product_in_specific_categories");
        when()
                .get("/products/category/jewelery")
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .time(lessThan(3000L))
                .body("size()", equalTo(4))
                .body("category", everyItem(equalTo("jewelery")));
    }

    @Test
    public void limit_result_of_product(){
        log.info("Starting test: limit_result_of_product");
        given()
                .queryParam("limit",5)
        .when()
                .get("/products")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("[4].id",equalTo(5))
                .time(lessThan(3000L))
                .assertThat().body("size()",equalTo(5));
    }

    @Test
    public void Sort_result_of_products_DESC() {
        log.info("Starting test: Sort_result_of_products_DESC");
        given()
                .queryParam("sort", "desc")
                .when()
                .get("products")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("[0].id", equalTo(20))
                .assertThat().body("[19].id", equalTo(1))
                .time(lessThan(3000L))
                .assertThat().body("size()", equalTo(20));
    }

    @Test
    public void update_product_using_PUT(){
        log.info("Starting test: update_product_using_PUT");
        Product product = new Product(
                "New POJO Product",
                22.5,
                "This is a new product created using POJO",
                "https://fakestoreapi.com/img.jpg",
                "electronics"
        );

        given()
                .contentType("application/json")
                .body(product)
                .when()
                .put("/products/1")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("title", equalTo("New POJO Product"))
                .assertThat().body("id",equalTo(1))
                .time(lessThan(3000L));
    }

    @Test
    public void update_product_using_PATCH() {
        log.info("Starting test: update_product_using_PATCH");
        Product product = new Product(
                "New POJO Product",
                22.99,
                "This is a new product created using POJO",
                "https://fakestoreapi.com/img.jpg",
                "electronics"
        );

        given()
                .contentType("application/json")
                .body(product)
                .when()
                .patch("/products/1")
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("title", equalTo("New POJO Product"))
                .body("id", equalTo(1))
                .time(lessThan(3000L));
    }

    @Test
    public void delete_a_specific_product() {
        log.info("Starting test: delete_a_specific_product");
        when()
                .delete("/products/1")
                .then()
                .assertThat().statusCode(200)
                .body("id", equalTo(1))
                .time(lessThan(6000L));
    }
}
