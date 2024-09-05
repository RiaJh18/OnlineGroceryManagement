
package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class user_management {

    private Response response;

    @Given("the API is running")
    public void theAPIIsRunning() {
        RestAssured.baseURI = "http://localhost:3000";
    }

    @When("I request all users")
    public void iRequestAllUsers() {
        response = RestAssured.get("/users");
    }

    @Then("I should receive a list of users")
    public void iShouldReceiveAListOfUsers() {
        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getBody().jsonPath().getList("id"), not(empty()));
    }

    @When("I register a new user with username {string}, password {string}, and email {string}")
    public void iRegisterANewUser(String username, String password, String email) {
        response = RestAssured
            .given()
            .contentType("application/json")
            .body("{\"username\":\"" + username + "\", \"password\":\"" + password + "\", \"email\":\"" + email + "\"}")
            .post("/users/register");
    }

    @Then("I should receive a success message and the new user should be added")
    public void iShouldReceiveASuccessMessageAndTheNewUserShouldBeAdded() {
        assertThat(response.getStatusCode(), is(201));
        assertThat(response.getBody().jsonPath().getString("username"), is(notNullValue()));
    }

    @When("I login with username {string} and password {string}")
    public void iLoginWithUsernameAndPassword(String username, String password) {
        response = RestAssured
            .given()
            .contentType("application/json")
            .body("{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}")
            .post("/users/login");
    }

    @Then("I should receive a success message and the user details")
    public void iShouldReceiveASuccessMessageAndTheUserDetails() {
        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getBody().jsonPath().getString("message"), is("Login successful"));
    }

    @When("I request all products")
    public void iRequestAllProducts() {
        response = RestAssured.get("/products");
    }

    @Then("I should receive a list of products")
    public void iShouldReceiveAListOfProducts() {
        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getBody().jsonPath().getList("id"), not(empty()));
    }

    @When("I add a new product with name {string}, category {string}, price {int}, and stock quantity {int}")
    public void iAddANewProduct(String name, String category, int price, int stockQuantity) {
        response = RestAssured
            .given()
            .contentType("application/json")
            .body("{\"name\":\"" + name + "\", \"category\":\"" + category + "\", \"price\":" + price + ", \"stockQuantity\":" + stockQuantity + "}")
            .post("/products");
    }

    @Then("I should receive a success message and the new product should be added")
    public void iShouldReceiveASuccessMessageAndTheNewProductShouldBeAdded() {
        assertThat(response.getStatusCode(), is(201));
        assertThat(response.getBody().jsonPath().getString("name"), is(notNullValue()));
    }

    @When("I place an order with user ID {int}, product ID {int}, quantity {int}, and total price {int}")
    public void iPlaceAnOrderWithDetails(int userId, int productId, int quantity, int totalPrice) {
        response = RestAssured
            .given()
            .contentType("application/json")
            .body("{\"userId\":" + userId + ", \"productId\":" + productId + ", \"quantity\":" + quantity + ", \"totalPrice\":" + totalPrice + "}")
            .post("/orders");
    }

    @Then("I should receive a success message and the new order should be added")
    public void iShouldReceiveASuccessMessageAndTheNewOrderShouldBeAdded() {
        assertThat(response.getStatusCode(), is(201));
        assertThat(response.getBody().jsonPath().getInt("userId"), is(notNullValue()));
    }
}
