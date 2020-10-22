package com.intuit.networthcalculator.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class AssetType {
    private UUID assesId;
    private String name;
    private BigDecimal amount;

    public AssetType( UUID assesId, String name, BigDecimal amount) {
        this.assesId = assesId;
        this.name = name;
        this.amount = amount;
    }

    public AssetType() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssetType assetType = (AssetType) o;
        return Objects.equals(assesId, assetType.assesId) &&
                Objects.equals(name, assetType.name) &&
                Objects.equals(amount, assetType.amount);
    }

    @Override
    public String toString() {
        return "AssetType{" +
                "assitId=" + assesId +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
    @Override
    public int hashCode() {
        return Objects.hash(assesId, name, amount);
    }

    public UUID getAssesId() {
        return assesId;
    }
    public String getAssetIdString(){
        return  assesId.toString();
    }

    public void setAssesId(UUID assesId) {
        this.assesId = assesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

     public BigDecimal getAmount() {
        if (amount != null) {
             return amount;
         }
        return new BigDecimal(0.00);

    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
