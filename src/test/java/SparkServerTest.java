import com.genericmethod.utxoexplorer.App;
import com.genericmethod.utxoexplorer.api.UnspentTransactionApi;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import spark.Spark;

public class SparkServerTest {

    private static final Integer port = 8089;
    private static final String host = "localhost";

    @ClassRule
    public static WireMockRule wireMockRule = new WireMockRule(port);

    @BeforeClass
    public static void setup() {
        App app = new App(new UnspentTransactionApi(host, port.toString()));
        app.start();
    }

    @AfterClass
    public static void teardown(){
        Spark.stop();
    }
}
