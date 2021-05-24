package me.fernando.quarkus.resource;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.DisabledOnNativeImage;
import io.quarkus.test.junit.QuarkusTest;
import me.fernando.quarkus.MovieResource;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@DisabledOnNativeImage
@TestHTTPEndpoint(MovieResource.class)
public class MovieResourceTest {

    @Test
    public void testCountMoviesEndpoint() {
        given()
                .when().get("/count")
                .then().statusCode(Response.Status.OK.getStatusCode())
                    .body(is(equalTo("0")))
        ;
    }

}
