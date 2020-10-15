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
    List<AssetType> getAssetTypes(){
        return  assetTypeDataAccessService.getAssetTypes();
    }

    public List<AssetType> saveAsset (AssetType asset){
       return assetTypeDataAccessService.saveAsset(asset.getName(), asset.getAmount());

    }
//    public void deleteAsset(String Id){
//        assetTypeDataAccessService.deleteAsset(Id);
//    }

    public AssetType findById(String id) {
        return assetTypeDataAccessService.findById(id);
    }
}
