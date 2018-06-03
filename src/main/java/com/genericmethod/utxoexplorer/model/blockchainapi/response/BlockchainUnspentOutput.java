package com.genericmethod.utxoexplorer.model.blockchainapi.response;

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class BlockchainUnspentOutput {

    @SerializedName("tx_age")
    private final String txAge;

    @SerializedName("tx_hash")
    private final String txHash;

    @SerializedName("tx_index")
    private final String txIndex;

    @SerializedName("tx_output_n")
    private final String txOutputN;

    @SerializedName("script")
    private final String script;

    @SerializedName("value")
    private final String value;

    public BlockchainUnspentOutput(String txAge,
                                   String txHash,
                                   String txIndex,
                                   String txOutputN,
                                   String script,
                                   String value) {
        this.txAge = txAge;
        this.txHash = txHash;
        this.txIndex = txIndex;
        this.txOutputN = txOutputN;
        this.script = script;
        this.value = value;
    }

    public String getTxAge() {
        return txAge;
    }
    public String getTxHash() {
        return txHash;
    }
    public String getTxIndex() {
        return txIndex;
    }
    public String getTxOutputN() {
        return txOutputN;
    }
    public String getScript() {
        return script;
    }
    public String getValue() {
        return value;
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