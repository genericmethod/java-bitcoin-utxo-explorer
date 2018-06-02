package com.genericmethod.utxoexplorer;

public class Path {

    public static class BlockchainApi {
        public static String URL = "http://blockchain.info";
    }

    static class Api {
        static final String GET_UNSPENT_TRANSACTIONS = "/address/:bitcoin_address";
        static final String HEALTHCHECK = "/healthcheck";
    }
}
