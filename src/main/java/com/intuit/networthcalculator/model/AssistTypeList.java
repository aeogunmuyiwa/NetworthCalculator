package com.intuit.networthcalculator.model;

import java.math.BigDecimal;
import java.util.List;

public class AssistTypeList {
    public AssistTypeList(String assetTypeName, List<AssetType> assetTypeList, NetworthType networthType) {
        AssetTypeName = assetTypeName;
        AssetTypeList = assetTypeList;
        this.networthType = networthType;
        if (this.getSum() == null) {
            this.sum = new BigDecimal(0.00);
        }else{
            this.sum = this.getSum() ;
        }

    }

    public NetworthType getNetworthType() {
        return networthType;
    }

    public void setNetworthType(NetworthType networthType) {
        this.networthType = networthType;
    }



    private NetworthType networthType;
    private  String AssetTypeName;
    private List<AssetType> AssetTypeList;
    private BigDecimal sum;
    public String getAssetTypeName() {
        return AssetTypeName;
    }

    public void setAssetTypeName(String assetTypeName) {
        AssetTypeName = assetTypeName;
    }

    public List<AssetType> getAssetTypeList() {
        return AssetTypeList;
    }

    public void setAssetTypeList(List<AssetType> assetTypeList) {
        AssetTypeList = assetTypeList;
    }

    public BigDecimal getSum() {
        var item = 0;
        BigDecimal sum = new BigDecimal(0.00);
        for (AssetType element : getAssetTypeList()){
            sum = sum.add(element.getAmount());
           // sum += element.getAmount();
        }
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
    @Override
    public String toString() {
        return "AssistTypeList{" +
                "networthType=" + networthType +
                ", AssetTypeName='" + AssetTypeName + '\'' +
                ", AssetTypeList=" + AssetTypeList +
                ", sum=" + sum +
                '}';
    }

}
