package com.genericmethod.utxoexplorer.model.utxoexplorer.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class UnspentOutput {

    @SerializedName("tx_hash")
    @Expose
    private String txHash;

    @SerializedName("tx_output_n")
    @Expose
    private String txOutputN;

    @SerializedName("value")
    @Expose
    private String value;

    public UnspentOutput(String txHash, String txOutputN, String value) {
        this.txHash = txHash;
        this.txOutputN = txOutputN;
        this.value = value;
    }

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    public String getTxOutputN() {
        return txOutputN;
    }

    public void setTxOutputN(String txOutputN) {
        this.txOutputN = txOutputN;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("txHash", txHash)
                .append("txOutputN", txOutputN)
                .append("value", value)
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(txOutputN)
                .append(value)
                .append(txHash)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof UnspentOutput) == false) {
            return false;
        }
        UnspentOutput rhs = ((UnspentOutput) other);
        return new EqualsBuilder().append(txOutputN, rhs.txOutputN)
                .append(value, rhs.value)
                .append(txHash, rhs.txHash)
                .isEquals();
    }

}