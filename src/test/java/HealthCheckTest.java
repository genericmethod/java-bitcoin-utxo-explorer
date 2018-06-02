import com.genericmethod.utxoexplorer.App;
import com.genericmethod.utxoexplorer.api.UnspentTransactionApi;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.RestAssured;
import org.junit.*;
import spark.Spark;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class HealthCheckTest {

    @BeforeClass
    public static void setup() {
        App app = new App(new UnspentTransactionApi("localhost","8089"));
        app.start();
    }

    @Test
    public void testHealthCheck() {
        given().
        when().get("http://localhost:4567/healthcheck")
                .then()
                .body(equalTo("OK"))
                .statusCode(200);
    }

    @AfterClass
    public static void teardown(){
        Spark.stop();
    }
}
