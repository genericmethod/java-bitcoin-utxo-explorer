package com.genericmethod.utxoexplorer.service;

import com.genericmethod.utxoexplorer.model.blockchainapi.response.BlockchainUnspentTransactionOutputsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * This class is used by Retrofit to generate client endpoints related
 * to the unspent resource.
 */
public interface UnspentTransactionService {

    @GET("/unspent")
    Call<BlockchainUnspentTransactionOutputsResponse> getUnspentTransactionOutputs(@Query("active") String address);

}
