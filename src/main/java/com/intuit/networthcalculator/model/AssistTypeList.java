package com.intuit.networthcalculator.model;

import java.util.List;

public class AssistTypeList {
    public AssistTypeList(String assetTypeName, List<AssetType> assetTypeList) {
        AssetTypeName = assetTypeName;
        AssetTypeList = assetTypeList;
        this.sum = getSum();
    }

    private  String AssetTypeName;
    private List<AssetType> AssetTypeList;
    private Double sum;
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

    public Double getSum() {
        var item = 0;
        Double sum = 0.0;
        for (AssetType element : getAssetTypeList()){
            sum += element.getAmount();

        }
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }


}
