package com.genericmethod.utxoexplorer.api;

import com.genericmethod.utxoexplorer.App;
import com.genericmethod.utxoexplorer.HttpStatusEnum;
import com.genericmethod.utxoexplorer.Path;
import com.genericmethod.utxoexplorer.model.blockchainapi.response.BlockchainUnspentTransactionOutputsResponse;
import com.genericmethod.utxoexplorer.model.utxoexplorer.response.UnspentOutput;
import com.genericmethod.utxoexplorer.model.utxoexplorer.response.UnspentTransactionOutputsResponse;
import com.genericmethod.utxoexplorer.service.UnspentTransactionService;
import com.google.gson.Gson;
import org.bitcoinj.core.AddressFormatException;
import org.bitcoinj.core.Base58;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class UnspentTransactionApi {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(App.class);

    private Retrofit retrofit;

    public UnspentTransactionApi() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Path.BlockchainApi.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public UnspentTransactionApi(String host, String port) {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://" + host + ":" + port)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Object getUnspentTransactions(Request request, Response response) throws IOException {

        String address = request.params(":bitcoin_address");

        log.info(String.format("Calling getUnspentTransactions - address:%s", address));

        try {

            log.info(String.format("Checking if address is a Base58 address:%s", address));
            Base58.decodeChecked(address);

        } catch (AddressFormatException afex) {
            log.info(String.format("Address format should be Base58 - address:%s", address));
            response.status(HttpStatusEnum.UNPROCESSABLE_ENTITY.getCode());
            return String.format("Address format should be Base58 - address:%s", address);
        }

        UnspentTransactionService service = retrofit.create(UnspentTransactionService.class);

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

            log.info(String.format("Call to blockchain api was unsuccessful - statusCode:%s message:%s",
                    unspentTransactionsResponse.code(),
                    unspentTransactionsResponse.errorBody().string()));

            response.status(HttpStatusEnum.BAD_REQUEST.getCode());
            return String.format("Call to blockchain api failed - reason:%s",
                    unspentTransactionsResponse.message());
        }


    }
}
