import com.genericmethod.utxoexplorer.App;
import com.genericmethod.utxoexplorer.api.UnspentTransactionApi;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import spark.Spark;

public class SparkServerTest {

    private static final Integer WIREMOCK_PORT = 8089;
    public static final String API_BASE_URL = "http://localhost:8080";
    public static final String BLOCKCHAIN_API_BASE_URL = "http://localhost:8089";

    @ClassRule
    public static WireMockRule wireMockRule = new WireMockRule(WIREMOCK_PORT);

    @BeforeClass
    public static void setup() {
        App app = new App(new UnspentTransactionApi(BLOCKCHAIN_API_BASE_URL));
        app.start();
    }

    @After
    public void reset(){
        WireMock.reset();
    }

    @AfterClass
    public static void teardown(){
        Spark.stop();
    }
}
