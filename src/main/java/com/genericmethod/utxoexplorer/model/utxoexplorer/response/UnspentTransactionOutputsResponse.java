package com.genericmethod.utxoexplorer.model.utxoexplorer.response;

import com.genericmethod.utxoexplorer.model.blockchainapi.response.BlockchainUnspentOutput;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class UnspentTransactionOutputsResponse {

    @SerializedName("outputs")
    @Expose
    private List<UnspentOutput> outputs = null;

    public UnspentTransactionOutputsResponse() {
    }

    public UnspentTransactionOutputsResponse(List<UnspentOutput> outputs) {
        this.outputs = outputs;
    }

    public List<UnspentOutput> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<UnspentOutput> outputs) {
        this.outputs = outputs;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("outputs", outputs)
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(outputs)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof UnspentTransactionOutputsResponse) == false) {
            return false;
        }
        UnspentTransactionOutputsResponse rhs = ((UnspentTransactionOutputsResponse) other);
        return new EqualsBuilder().append(outputs, rhs.outputs).isEquals();
    }

}