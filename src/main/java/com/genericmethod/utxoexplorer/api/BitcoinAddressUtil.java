package com.genericmethod.utxoexplorer.api;

import org.bitcoinj.core.AddressFormatException;
import org.bitcoinj.core.Base58;

public class BitcoinAddressUtil {


    /**
     * Check if a bitcoin address is valid.
     * @param address The address to be validated
     * @return True if the address is valid, otherwise false.
     */
    public static Boolean isValidAddress(String address) {

        try {
            Base58.decodeChecked(address);
            return true;
        } catch (AddressFormatException afex) {
            return false;
        }
    }

    public static Boolean isNotValidAddress(String address){
        return !isValidAddress(address);
    }
}

