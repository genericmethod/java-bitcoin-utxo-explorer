package com.genericmethod.utxoexplorer;

import com.genericmethod.utxoexplorer.api.UnspentTransactionApi;
import com.genericmethod.utxoexplorer.model.blockchainapi.response.BlockchainUnspentTransactionOutputsResponse;
import com.genericmethod.utxoexplorer.model.utxoexplorer.response.UnspentTransactionOutputsResponse;
import com.google.gson.Gson;
import org.slf4j.LoggerFactory;
import static spark.Spark.get;


public class App {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(App.class);

    private UnspentTransactionApi unspentTransactionApi;

    public App(){
        unspentTransactionApi = new UnspentTransactionApi();
    }

    public void start () {

        log.info("Starting Server");
        Gson gson = new Gson();
        get("/healthcheck", (req, res) -> "OK");
        get("/address/:bitcoin_address", (req, res) -> {

            //get path parameter
            final String bitcoin_address = req.params(":bitcoin_address");

            //call blockchain api
            final UnspentTransactionOutputsResponse unspentTransactions = unspentTransactionApi.getUnspentTransactions(bitcoin_address);

            //return results
            return unspentTransactions;
        }, gson::toJson);

    }
}
