package adapter;

import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;
import lombok.Setter;
import model.Booking;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

@AllArgsConstructor
@Setter
public abstract class BaseAdapter {

    RequestSpecification requestSpec = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setBaseUri("http://localhost:3001/")
            .build();

    private String endpoint;
    protected Gson gson = new Gson();

    public BaseAdapter(String endpoint) {
        this.endpoint = endpoint;
    }

    public ValidatableResponse post(String payload) {
        return given()
                .spec(requestSpec)
                .body(payload)
                .when()
                .post(endpoint)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    public ValidatableResponse post(Booking model) {
        return given()
                .spec(requestSpec)
                .body(model)
                .when()
                .post(endpoint)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    public ValidatableResponse get() {
        return given()
                .spec(requestSpec)
                .when()
                .get(endpoint)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    public ValidatableResponse delete(String token) {
        return given()
                .spec(requestSpec)
                .header("Cookie", "token=" + token)
                .when()
                .delete(endpoint)
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    }

    public ValidatableResponse notFound() {
        return given()
                .spec(requestSpec)
                .when()
                .get(endpoint)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    public ValidatableResponse put(Booking model, String token) {
        return given()
                .spec(requestSpec)
                .header("Cookie", "token=" + token)
                .body(model)
                .when()
                .put(endpoint)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    public ValidatableResponse patch(String payload, String token) {
        return given()
                .spec(requestSpec)
                .header("Cookie", "token=" + token)
                .body(payload)
                .when()
                .patch(endpoint)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    public ValidatableResponse created() {
        return given()
                .spec(requestSpec)
                .when()
                .get(endpoint)
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    }

    public void bookingUrnConf(String endpoint, int id) {
        setEndpoint(String.format(endpoint, id));
    }

}
