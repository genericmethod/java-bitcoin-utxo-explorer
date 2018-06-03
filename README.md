# UTXO Explorer

A tiny API to explore unspent bitcoin transactions

- Built with Java 8 / Spark Web framework and bitcoinj
- Tested with Wiremock and Rest-Assured.
- Requires Gradle
- Uses the Blockchain.info API as a data source


## Usage

```
git clone https://github.com/genericmethod/java-bitcoin-utxo-explorer.git
```
```
./gradlew build
```
```
java -jar build/libs/java-bitcoin-utxo-explorer-1.0-SNAPSHOT.jar
```
or just run this single command
```
git clone https://github.com/genericmethod/java-bitcoin-utxo-explorer.git && cd java-bitcoin-utxo-explorer  && ./gradlew build && java -jar build/libs/java-bitcoin-utxo-explorer-1.0-SNAPSHOT.jar
```


# API Endpoints

```
- GET /address/:bitcoin_address - get all unspent transactions
- GET /healthcheck - ping the server for health

```

## Http Status Code Summary

```
200 OK - Response to a successful GET, PUT, PATCH or DELETE.
400 Bad Request - The request is malformed, such as if the body does not parse
422 Unprocessable Entity - Used for validation errors
404 Not Found - When a non-existent resource is requested
```

## Address Resources

## GET /address/:bitcoin_address
Returns all unspent transaction outputs for a bitcoin address.

##### Example

##### Request

```
GET /address/1MHH6FcZNwN4vvNfbSzjHQ85c8kABiJ7Lu
```

##### Response
```
{
  "outputs":[
    {
      "tx_hash":"e6452a2cb71aa864aaa959e647e7a4726a22e640560f199f79b56b5502114c37",
      "tx_output_n":"0",
      "value":"5000661330"
    }
  ]
}
```
##### Status

200




