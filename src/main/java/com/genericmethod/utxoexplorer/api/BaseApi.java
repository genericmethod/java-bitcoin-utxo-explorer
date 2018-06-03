package com.genericmethod.utxoexplorer.api;

import com.genericmethod.utxoexplorer.Path;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseApi {

    private Retrofit retrofit;

    public BaseApi() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Path.BlockchainApi.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public BaseApi(String baseUrl) {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public Retrofit getRetrofit() {
        return retrofit;
    }
}
