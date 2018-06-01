
import com.genericmethod.utxoexplorer.App;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasSize;

public class UnspentTransactionsEndpointTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    //https://blockchain.info/unspent?active=$address

    @BeforeClass
    public static void setup() {
        App app = new App();
        app.start();
        RestAssured.port = 4567;
    }

    @Test
    public void testNoUnspentTransactionsFound(){

        String noUnspentOutputResponse =
                "{\n" +
                "  \"unspent_outputs\":[]\n" +
                "}";

        String addressThatReturnsEmptyArray = "1";

        //Stub the call being made to the blockchain api
        stubFor(get("/unspent?active="+addressThatReturnsEmptyArray)
                .willReturn(okJson(noUnspentOutputResponse)));

        when().
                get("/address/" + addressThatReturnsEmptyArray).
                then().
                statusCode(200).
                body("outputs", hasSize(0));

        WireMock.reset();

    }

    @Test
    public void testSingleUnspentTranasctionFound(){

        String singleUnspentOutputResponse =
                "{\n" +
                        "  \"unspent_outputs\":[\n" +
                        "    {\n" +
                        "      \"tx_age\":\"1322659106\",\n" +
                        "      \"tx_hash\":\"e6452a2cb71aa864aaa959e647e7a4726a22e640560f199f79b56b5502114c37\",\n" +
                        "      \"tx_index\":\"12790219\",\n" +
                        "      \"tx_output_n\":\"0\",\n" +
                        "      \"script\":\"76a914641ad5051edd97029a003fe9efb29359fcee409d88ac\",\n" +
                        "      \"value\":\"5000661330\"\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}";

        String addressThatReturnsOneOutput = "2";

        //Stub the call being made to the blockchain api
        stubFor(get("/unspent?active=" + addressThatReturnsOneOutput)
                .willReturn(okJson(singleUnspentOutputResponse)));

        when().
                get("/address/" + addressThatReturnsOneOutput).
                then().
                statusCode(200).
                body("outputs", hasSize(1));

        WireMock.reset();


    }

    @Test
    public void testMultipleUnspentTransactionsFound(){

        String multipleUnspentOutputResponse =
                "{\n" +
                        "  \"unspent_outputs\":[\n" +
                        "    {\n" +
                        "      \"tx_age\":\"1322659106\",\n" +
                        "      \"tx_hash\":\"e6452a2cb71aa864aaa959e647e7a4726a22e640560f199f79b56b5502114c37\",\n" +
                        "      \"tx_index\":\"12790219\",\n" +
                        "      \"tx_output_n\":\"0\",\n" +
                        "      \"script\":\"76a914641ad5051edd97029a003fe9efb29359fcee409d88ac\",\n" +
                        "      \"value\":\"5000661330\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"tx_age\":\"1322659106\",\n" +
                        "      \"tx_hash\":\"e6452a2cb71aa864aaa959e647e7a4726a22e640560f199f79b56b5502114c37\",\n" +
                        "      \"tx_index\":\"12790219\",\n" +
                        "      \"tx_output_n\":\"0\",\n" +
                        "      \"script\":\"76a914641ad5051edd97029a003fe9efb29359fcee409d88ac\",\n" +
                        "      \"value\":\"5000661330\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"tx_age\":\"1322659106\",\n" +
                        "      \"tx_hash\":\"e6452a2cb71aa864aaa959e647e7a4726a22e640560f199f79b56b5502114c37\",\n" +
                        "      \"tx_index\":\"12790219\",\n" +
                        "      \"tx_output_n\":\"0\",\n" +
                        "      \"script\":\"76a914641ad5051edd97029a003fe9efb29359fcee409d88ac\",\n" +
                        "      \"value\":\"5000661330\"\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}";


        String addressThatReturnMultipleUnspentOutputs = "3";

        //Stub the call being made to the blockchain api
        stubFor(get("/unspent?active=" + addressThatReturnMultipleUnspentOutputs)
                .willReturn(okJson(multipleUnspentOutputResponse)));

        when().
                get("/address/" + addressThatReturnMultipleUnspentOutputs).
                then().
                statusCode(200).
                body("outputs", hasSize(3));

        WireMock.reset();

    }

}
