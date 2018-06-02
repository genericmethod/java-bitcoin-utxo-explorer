
import com.genericmethod.utxoexplorer.App;
import com.genericmethod.utxoexplorer.api.UnspentTransactionApi;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;

public class UnspentTransactionsEndpointTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @BeforeClass
    public static void setup() {
        App app = new App(new UnspentTransactionApi("localhost","8089"));
        app.start();
        RestAssured.port = 4567;
    }

    @Test
    public void testBlockchainApiUnavailable(){

        String validAddress = "1MDUoxL1bGvMxhuoDYx6i11ePytECAk9QK";

        //Stub the call being made to the blockchain api
        stubFor(get("/unspent?active="+validAddress)
                .willReturn(aResponse()
                        .withStatus(500)));

        when().
                get("/address/" + validAddress).
                then().
                statusCode(400).
                body(equalTo("Call to blockchain api failed for the following reason:Server Error"));

    }

    @Test
    public void testInvalidAddress(){

        String invalidAddress = "elon_musk_is_satoshi_nakamoto";

        when().
                get("/address/" + invalidAddress).
                then().
                statusCode(422).
                body(equalTo("Address format should be Base58 address:elon_musk_is_satoshi_nakamoto"));

    }

    @Test
    public void testNoUnspentTransactionsFound(){

        String noUnspentOutputResponse =
                "{\n" +
                "  \"unspent_outputs\":[]\n" +
                "}";

        String addressThatReturnsEmptyArray = "1MDUoxL1bGvMxhuoDYx6i11ePytECAk9QK";

        //Stub the call being made to the blockchain api
        stubFor(get("/unspent?active="+addressThatReturnsEmptyArray)
                .willReturn(okJson(noUnspentOutputResponse)));

        String resp = "{\"outputs\":[]}";

        when().
                get("/address/" + addressThatReturnsEmptyArray).
                then().
                statusCode(200).
                body(equalTo(resp));

        WireMock.reset();

    }

    @Test
    public void testSingleUnspentTransactionFound(){

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

        String addressThatReturnsOneOutput = "1NU7uRQHYYCmchJCAJcsb8bbGNEeoynQDN";

        //Stub the call being made to the blockchain api
        stubFor(get("/unspent?active=" + addressThatReturnsOneOutput)
                .willReturn(okJson(singleUnspentOutputResponse)));

        String uxtoExplorerResponse = "{\"outputs\":[{\"tx_hash\":\"e6452a2cb71aa864aaa959e647e7a4726a22e640560f199f79b56b5502114c37\",\"tx_output_n\":\"0\",\"value\":\"5000661330\"}]}";

        when().
                get("/address/" + addressThatReturnsOneOutput).
                then().
                statusCode(200).
                body(equalTo(uxtoExplorerResponse));

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


        String addressThatReturnsMultipleUnspentOutputs = "17A16QmavnUfCW11DAApiJxp7ARnxN5pGX";

        //Stub the call being made to the blockchain api
        stubFor(get("/unspent?active=" + addressThatReturnsMultipleUnspentOutputs)
                .willReturn(okJson(multipleUnspentOutputResponse)));

        String resp = "{\"outputs\":[{\"tx_hash\":\"e6452a2cb71aa864aaa959e647e7a4726a22e640560f199f79b56b5502114c37\",\"tx_output_n\":\"0\",\"value\":\"5000661330\"},{\"tx_hash\":\"e6452a2cb71aa864aaa959e647e7a4726a22e640560f199f79b56b5502114c37\",\"tx_output_n\":\"0\",\"value\":\"5000661330\"},{\"tx_hash\":\"e6452a2cb71aa864aaa959e647e7a4726a22e640560f199f79b56b5502114c37\",\"tx_output_n\":\"0\",\"value\":\"5000661330\"}]}";

        when().
                get("/address/" + addressThatReturnsMultipleUnspentOutputs).
                then().
                statusCode(200).
                body(equalTo(resp));

        WireMock.reset();

    }

}
