package com.genericmethod.utxoexplorer.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class UnspentTransactionOutputsResponse {

    @SerializedName("unspent_outputs")
    @Expose
    private List<UnspentOutput> unspentOutputs = null;

    public List<UnspentOutput> getUnspentOutputs() {
        return unspentOutputs;
    }

    public void setUnspentOutputs(List<UnspentOutput> unspentOutputs) {
        this.unspentOutputs = unspentOutputs;
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
        if ((other instanceof UnspentTransactionOutputsResponse) == false) {
            return false;
        }
        UnspentTransactionOutputsResponse rhs = ((UnspentTransactionOutputsResponse) other);
        return new EqualsBuilder().append(unspentOutputs, rhs.unspentOutputs).isEquals();
    }

}