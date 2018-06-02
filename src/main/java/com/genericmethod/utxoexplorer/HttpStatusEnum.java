package com.genericmethod.utxoexplorer;

public enum HttpStatusEnum {

    OK(200),
    BAD_REQUEST(400),
    NOT_FOUND(404),
    PRECONDITION_FAILED(412),
    UNPROCESSABLE_ENTITY(422);

    private int statusCode;

    HttpStatusEnum(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getCode(){
        return this.statusCode;
    }
}
