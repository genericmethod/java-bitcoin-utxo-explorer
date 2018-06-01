package com.genericmethod.utxoexplorer.model.blockchainapi.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class BlockchainUnspentOutput {

    @SerializedName("tx_age")
    @Expose
    private String txAge;

    @SerializedName("tx_hash")
    @Expose
    private String txHash;

    @SerializedName("tx_index")
    @Expose
    private String txIndex;

    @SerializedName("tx_output_n")
    @Expose
    private String txOutputN;

    @SerializedName("script")
    @Expose
    private String script;

    @SerializedName("value")
    @Expose
    private String value;

    public String getTxAge() {
        return txAge;
    }

    public void setTxAge(String txAge) {
        this.txAge = txAge;
    }

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    public String getTxIndex() {
        return txIndex;
    }

    public void setTxIndex(String txIndex) {
        this.txIndex = txIndex;
    }

    public String getTxOutputN() {
        return txOutputN;
    }

    public void setTxOutputN(String txOutputN) {
        this.txOutputN = txOutputN;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
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
                .append("txAge", txAge)
                .append("txHash", txHash)
                .append("txIndex", txIndex)
                .append("txOutputN", txOutputN)
                .append("script", script)
                .append("value", value)
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(txOutputN)
                .append(txAge).append(value)
                .append(txHash).append(txIndex)
                .append(script).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof BlockchainUnspentOutput) == false) {
            return false;
        }
        BlockchainUnspentOutput rhs = ((BlockchainUnspentOutput) other);
        return new EqualsBuilder().append(txOutputN, rhs.txOutputN).append(txAge, rhs.txAge).append(value, rhs.value).append(txHash, rhs.txHash).append(txIndex, rhs.txIndex).append(script, rhs.script).isEquals();
    }

}