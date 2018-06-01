package com.genericmethod.utxoexplorer.service;

import com.genericmethod.utxoexplorer.model.blockchainapi.response.BlockchainUnspentTransactionOutputsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UnspentTransactionService {

    //https://blockchain.info/unspent?active=$address
    @GET("/unspent")
    Call<BlockchainUnspentTransactionOutputsResponse> getUnspentTransationOutputs(@Query("active") String address);

}
