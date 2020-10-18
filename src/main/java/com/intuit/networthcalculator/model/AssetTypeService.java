package com.intuit.networthcalculator.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetTypeService {
    private final AssetTypeDataAccessService assetTypeDataAccessService;

    @Autowired
    public AssetTypeService(AssetTypeDataAccessService assetTypeDataAccessService) {
        this.assetTypeDataAccessService = assetTypeDataAccessService;
    }
    List<AssistTypeList> getAssetTypes(){
        return  assetTypeDataAccessService.getAssetTypes();
    }

    public List<AssistTypeList> saveAsset (int Category, AssetType asset){
       return assetTypeDataAccessService.saveAsset(Category,asset.getName(),asset.getAmount());

    }
    public void deleteAsset(String Id, int category){
        assetTypeDataAccessService.deleteAsset(Id, category);
    }

    public AssetType findById(String id, int Category) {
        return assetTypeDataAccessService.findById(id, Category);
    }
}
