package com.genericmethod.utxoexplorer.api;

import com.genericmethod.utxoexplorer.HttpStatusEnum;
import com.genericmethod.utxoexplorer.model.blockchainapi.response.BlockchainUnspentTransactionOutputsResponse;
import com.genericmethod.utxoexplorer.model.utxoexplorer.response.UnspentOutput;
import com.genericmethod.utxoexplorer.model.utxoexplorer.response.UnspentTransactionOutputsResponse;
import com.genericmethod.utxoexplorer.service.UnspentTransactionService;
import com.google.gson.Gson;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UnspentTransactionApi extends BaseApi {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UnspentTransactionApi.class);

    public UnspentTransactionApi() {
    }

    public UnspentTransactionApi(String baseUrl) {
        super(baseUrl);
    }

    public Object getUnspentTransactions(Request request, Response response) throws IOException {

        String address = request.params(":bitcoin_address");

        log.info(String.format("Calling getUnspentTransactions - address:%s", address));

        log.info(String.format("Checking if address is a Base58 address:%s", address));
        if (BitcoinAddressUtil.isNotValidAddress(address)) {
            log.info(String.format("Address format should be Base58 - address:%s", address));
            response.status(HttpStatusEnum.UNPROCESSABLE_ENTITY.getCode());
            return String.format("Address format should be Base58 - address:%s", address);
        }

        UnspentTransactionService service = getRetrofit().create(UnspentTransactionService.class);

        final Call<BlockchainUnspentTransactionOutputsResponse> unspentTransactionOutputs =
                service.getUnspentTransactionOutputs(address);

        log.info("Calling Blockchain API - unspent endpoint");
        final retrofit2.Response<BlockchainUnspentTransactionOutputsResponse> unspentTransactionsResponse =
                unspentTransactionOutputs.execute();

        if (unspentTransactionsResponse.isSuccessful()) {

            log.info("Blockchain API called successfully - unspent endpoint");

            List<UnspentOutput> unspentOutputList =
                    unspentTransactionsResponse.body().getUnspentOutputs().stream()
                            .map(utxo -> new UnspentOutput(utxo.getTxHash(), utxo.getTxOutputN(), utxo.getValue()))
                            .collect(Collectors.toList());

            UnspentTransactionOutputsResponse unspentTransactionOutputsResponse =
                    new UnspentTransactionOutputsResponse(unspentOutputList);

            response.status(HttpStatusEnum.OK.getCode());
            return new Gson().toJson(unspentTransactionOutputsResponse);

        } else {
            //The blockchain api /unspent endpoint returns status code 500
            //and the message "No free outputs to spend" when there are no unspent outputs
            //or the endpoint is used incorrectly.
            //This is being mapped to status code 200 and returning an empty array.
            //In this way we are representing "No free outputs to spend" as a
            // data structure rather than a message.
            log.info(String.format("No unspent outputs found - address:%s", address));

            response.status(HttpStatusEnum.OK.getCode());
            return new Gson().toJson(new UnspentTransactionOutputsResponse(new ArrayList<>()));
        }

    }
}
