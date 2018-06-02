package com.genericmethod.utxoexplorer;

import com.genericmethod.utxoexplorer.api.UnspentTransactionApi;
import org.slf4j.LoggerFactory;
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

        get(Path.HEALTHCHECK, (req, res) -> "OK");
        get(Path.UNSPENT_TRANSACTIONS, (req, res) -> unspentTransactionApi.getUnspentTransactions(req, res));

        log.info("UXTOExplorer has started ...");
    }

    public static void main(String[] args) {
        App app = new App();
        app.start();
    }
}
