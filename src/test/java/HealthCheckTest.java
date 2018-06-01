import com.genericmethod.utxoexplorer.App;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class HealthCheckTest {

    @BeforeClass
    public static void setup() {
        App app = new App();
        app.start();
        RestAssured.port = 4567;
    }

    @Test
    public void testHealthCheck() {
        given().
        when().get("/healthcheck")
                .then()
                .body(equalTo("OK"))
                .statusCode(200);
    }
}
