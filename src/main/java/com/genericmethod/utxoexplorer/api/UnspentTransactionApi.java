package com.genericmethod.utxoexplorer.api;

import com.genericmethod.utxoexplorer.App;
import com.genericmethod.utxoexplorer.model.blockchainapi.response.BlockchainUnspentTransactionOutputsResponse;
import com.genericmethod.utxoexplorer.model.utxoexplorer.response.UnspentOutput;
import com.genericmethod.utxoexplorer.model.utxoexplorer.response.UnspentTransactionOutputsResponse;
import com.genericmethod.utxoexplorer.service.UnspentTransactionService;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class UnspentTransactionApi {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(App.class);

    private Retrofit retrofit;

    public UnspentTransactionApi() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8089")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public UnspentTransactionOutputsResponse getUnspentTransactions(String address)
            throws IOException {

        log.info(String.format("Calling getUnspentTransactions address:%s", address));

        UnspentTransactionService service = retrofit.create(UnspentTransactionService.class);

        final Call<BlockchainUnspentTransactionOutputsResponse> unspentTransactionOutputs =
                service.getUnspentTransationOutputs(address);

        final Response<BlockchainUnspentTransactionOutputsResponse> response = unspentTransactionOutputs.execute();

        if (response.isSuccessful()) {

            log.info("Successful response");

            List<UnspentOutput> unspentOutputList =
                    response.body().getUnspentOutputs().stream()
                            .map(utxo -> new UnspentOutput(utxo.getTxHash(), utxo.getTxOutputN(), utxo.getValue()))
                            .collect(Collectors.toList());

            UnspentTransactionOutputsResponse resp =
                    new UnspentTransactionOutputsResponse(unspentOutputList);

            return resp;
        }
        
        return new UnspentTransactionOutputsResponse();
    }
}
