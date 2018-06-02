package com.genericmethod.utxoexplorer.model.blockchainapi.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class BlockchainUnspentTransactionOutputsResponse {

    @SerializedName("unspent_outputs")
    private final List<BlockchainUnspentOutput> unspentOutputs;

    public BlockchainUnspentTransactionOutputsResponse(List<BlockchainUnspentOutput> unspentOutputs) {
        this.unspentOutputs = unspentOutputs;
    }

    public List<BlockchainUnspentOutput> getUnspentOutputs() {
        return unspentOutputs;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("unspentOutputs", unspentOutputs).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(unspentOutputs).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof BlockchainUnspentTransactionOutputsResponse) == false) {
            return false;
        }
        BlockchainUnspentTransactionOutputsResponse rhs = ((BlockchainUnspentTransactionOutputsResponse) other);
        return new EqualsBuilder().append(unspentOutputs, rhs.unspentOutputs).isEquals();
    }

}