package com.genericmethod.utxoexplorer;

import com.genericmethod.utxoexplorer.api.UnspentTransactionApi;
import com.genericmethod.utxoexplorer.model.blockchainapi.response.BlockchainUnspentTransactionOutputsResponse;
import com.genericmethod.utxoexplorer.model.utxoexplorer.response.UnspentTransactionOutputsResponse;
import com.google.gson.Gson;
import org.slf4j.LoggerFactory;
import static spark.Spark.get;
import static spark.Spark.stop;


public class App {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(App.class);

    private UnspentTransactionApi unspentTransactionApi;

    public App(){
        unspentTransactionApi = new UnspentTransactionApi();
    }

    public App(UnspentTransactionApi unspentTransactionApi){
        this.unspentTransactionApi = unspentTransactionApi;
    }

    public void start () {

        log.info("Starting UXTOExplorer ... ");

        Gson gson = new Gson();

        get("/healthcheck", (req, res) -> "OK");
        get("/address/:bitcoin_address", (req, res) -> unspentTransactionApi.getUnspentTransactions(req, res));

        log.info("UXTOExplorer has started ...");
    }

    public static void main(String[] args) {
        App app = new App();
        app.start();
    }
}
