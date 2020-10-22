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

    /**
     * Get all data from datasource
     * */
    Networth getNetworthData (){
        return assetTypeDataAccessService.getNetworthData();
    }

    /**
     * Get totalNetworth from datasource
     * */
    List<AssetMain> getNetworth(){
        return assetTypeDataAccessService.getNetworth();
    }

    /**
     * Get asset from datasource
     * */
    List<AssistTypeList> getAssetTypes(){
        return  assetTypeDataAccessService.getAssetTypes();
    }

    /**
     * Save asset to datasource
     * */

    public List<AssistTypeList> saveAsset (int Category, AssetType asset){
       return assetTypeDataAccessService.saveAsset(Category,asset.getName(),asset.getAmount());
    }

    /**
     * Delete asset from datasource
     * */
    public void deleteAsset(String Id, int category){
        assetTypeDataAccessService.deleteAsset(Id, category);
    }

    /**
     * Find asset by id and category
     * */
    public AssetType findById(String id, int Category) {
        return assetTypeDataAccessService.findById(id, Category);
    }


    /**
     * Get Liablities from datasource
     * */
    List<AssistTypeList> getLiabilityTypes(){

        return  assetTypeDataAccessService.getLiabiltyTypes();
    }

    /**
     * Save Liabilities to datasource
     * */
    public List<AssistTypeList> saveLiability (int Category, AssetType asset){
        return assetTypeDataAccessService.saveLiability(Category,asset.getName(),asset.getAmount());

    }
    /**
     * Delete Libility from datasource
     * */
    public void deleteLiability(String Id, int category){
        assetTypeDataAccessService.deleteLiability(Id, category);
    }
    /**
     * Find Liability by id and category
     * */
    public AssetType findByLiabilityId(String id, int Category) {
        return assetTypeDataAccessService.findLiabilityById(id, Category);
    }
}
