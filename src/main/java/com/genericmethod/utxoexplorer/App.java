package com.genericmethod.utxoexplorer;

import org.slf4j.LoggerFactory;

import static spark.Service.ignite;
import static spark.Spark.get;
import static spark.Spark.init;

public class App {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(App.class);

    public App(){

    }

    public void start () {

        log.info("Starting Server");

        try {
            get("/healthcheck", (req, res) -> "OK");
        } catch (Exception ex){
            log.info(ex.getMessage());
        }
    }
}
