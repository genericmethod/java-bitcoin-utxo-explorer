package com.genericmethod.utxoexplorer;

import com.genericmethod.utxoexplorer.api.UnspentTransactionApi;
import org.slf4j.LoggerFactory;
import spark.Spark;

import static spark.Spark.get;


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

        Spark.port(AppConfig.API_PORT);

        get(Path.Api.HEALTHCHECK, (req, res) -> "OK");
        get(Path.Api.GET_UNSPENT_TRANSACTIONS, (req, res) -> unspentTransactionApi.getUnspentTransactions(req, res));

    }

    public static void main(String[] args) {
        App app = new App();
        app.start();
    }
}
